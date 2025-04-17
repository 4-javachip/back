package com.starbucks.back.payment.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.dto.in.RequestPaymentConfirmDto;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentConfirmDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.infrastructure.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    @Value("${payment.secret-key}")
    private String secretKey;

    @Value("${payment.base-url}")
    private String baseUrl;

    @Value("${payment.success-url}")
    private String successUrl;

    @Value("${payment.fail-url}")
    private String failUrl;

    /**
     * 결제 생성
     * @param requestPaymentCreateDto
     */
    @Transactional
    @Override
    public ResponsePaymentCreateDto addPayment(RequestPaymentCreateDto requestPaymentCreateDto
    ) {
        Payment payment = requestPaymentCreateDto.toEntity();

        // 결제 내역 db 저장
        paymentRepository.save(payment);

        // 결제 생성 api 정보
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();

        body.put("orderId", payment.getPaymentUuid());  // 결제 고유 ID
        body.put("amount", requestPaymentCreateDto.getTotalPurchasePrice());    // 실제 결제 금액
        body.put("orderName", requestPaymentCreateDto.getOrderName());
        body.put("method", requestPaymentCreateDto.getMethod());
        body.put("successUrl", successUrl);
        body.put("failUrl", failUrl);

        String auth = secretKey + ":";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(body, headers);

        // toss 결제 생성 API 호출
        ResponseEntity<Map> response = restTemplate.postForEntity(
                baseUrl + "/payments", httpRequest, Map.class);
        System.out.println("📦 Toss 응답 전체: " + response.getBody());

        Map responseBody = response.getBody();
        log.info("responseBody: {}", responseBody);
        // 결제 생성 결과 반환
        return ResponsePaymentCreateDto.builder()
                        .checkoutUrl(((Map<String, String>) responseBody.get("checkout")).get("url"))
                        .paymentUuid(payment.getPaymentUuid())
                        .build();

    }

    /**
     * 결제 승인
     * @param
     */
    @Transactional
    @Override
    public ResponsePaymentConfirmDto confirmPayment(RequestPaymentConfirmDto requestPaymentConfirmDto) {
        RestTemplate restTemplate = new RestTemplate();

        // Toss 승인 요청 바디 구성
        Map<String, Object> body = new HashMap<>();
        body.put("paymentKey", requestPaymentConfirmDto.getPaymentCode());
        body.put("orderId", requestPaymentConfirmDto.getPaymentUuid());
        body.put("amount", requestPaymentConfirmDto.getTotalPurchasePrice());

        // Basic 인증 헤더
        String auth = secretKey + ":";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Basic " + encodedAuth);

        // 요청 바디와 헤더를 HttpEntity로 감싸기
        HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(body, headers);

        // ✅ 결제 정보 갱신 (paymentCode, status)
        Payment payment = paymentRepository
                .findByPaymentUuid(requestPaymentConfirmDto.getPaymentUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PAYMENT_NO_EXIST));

        // 결제 승인 처리가 이미 완료된 경우
        if (payment.getStatus() != PaymentStatus.READY) {
            // 이미 완료된 결제는 무시
            throw new BaseException(BaseResponseStatus.PAYMENT_ALREADY_DONE);
        }

        try {
            // Toss 결제 승인 api 요청 (postForEntity)
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    baseUrl + "/payments/confirm", httpRequest, Map.class
            );

            Map responseBody = response.getBody();
            System.out.println("✅ 결제 승인 응답: " + responseBody);

            if (responseBody == null) {
                throw new BaseException(BaseResponseStatus.TOSS_EMPTY_RESPONSE);
            }

            String paymentCode = (String) responseBody.get("paymentKey");
            String paymentUuid = (String) responseBody.get("orderId");
            String method = (String) responseBody.get("method");
            Integer amount = (Integer) responseBody.get("totalAmount");
            PaymentStatus paymentStatus = PaymentStatus.valueOf((String) responseBody.get("status"));
            OffsetDateTime offsetDateTime = OffsetDateTime.parse((String) responseBody.get("approvedAt"));
            LocalDateTime approvedAt = offsetDateTime.toLocalDateTime();
            Map<String, String> failure = (Map<String, String>) responseBody.get("failure");

            // 결제 실패 시 관련 정보 파싱 + 저장, 이후 에러 처리
            if (failure != null) {
//                String failureCode = failure.get("code");
                String failReason = failure.get("message");
                System.out.println("결제 실패 사유(toss): " + failReason);

                paymentRepository.save(requestPaymentConfirmDto.updateFailPayment(
                        payment, failReason
                ));
                throw new BaseException(BaseResponseStatus.PAYMENT_CONFIRM_FAIL);
            }

            // 금액 불일치 시
            if (!Objects.equals(amount, payment.getTotalPurchasePrice())) {
                System.out.println("결제 금액 불일치: " + amount + " / " + payment.getTotalPurchasePrice());
                throw new BaseException(BaseResponseStatus.PAYMENT_AMOUNT_MISMATCH);
            }

            paymentRepository.save(requestPaymentConfirmDto.updateSuccessPayment(
                    payment, paymentCode, method, amount, paymentStatus, approvedAt));

            return ResponsePaymentConfirmDto.from(
                    "결제 완료", paymentUuid, paymentStatus, approvedAt.toString()
            );
        } catch (Exception e) {
            // 결제 승인 실패 시 처리
            System.out.println("❌ 결제 승인 실패: " + e.getMessage());
            throw e;
        }


    }
}
