package com.starbucks.back.payment.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.order.application.OrderListService;
import com.starbucks.back.order.domain.OrderList;
import com.starbucks.back.payment.domain.Payment;
import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.dto.in.RequestPaymentConfirmDto;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentConfirmDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentDto;
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
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final OrderListService orderListService;

    @Value("${payment.secret-key}")
    private String secretKey;

    @Value("${payment.base-url}")
    private String baseUrl;

    @Value("${payment.success-url}")
    private String successUrl;

    @Value("${payment.fail-url}")
    private String failUrl;

    @Value("${payment.callback-url}")
    private String callbackUrl;

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
        body.put("cashReceipt", Map.of("type", "소득공제")); // 현금영수증 자동 발급 (선택)
        body.put("validHours", 24); // 24시간 안에 입금 유효 (선택)
        body.put("virtualAccountCallbackUrl", callbackUrl); // 웹훅 URL 명시 가능
        // 가상계좌 결제 시 설정값
        body.put("useEscrow", false); // 👉 에스크로 사용 안 함
        body.put("cashReceipt", Map.of("type", "소득공제"));
        body.put("validHours", 1);


        String auth = secretKey + ":";
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> httpRequest = new HttpEntity<>(body, headers);

        // toss 결제 생성 API 호출
        ResponseEntity<Map> response = restTemplate.postForEntity(
                baseUrl + "/payments", httpRequest, Map.class);

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

        log.info("requestPaymentConfirmDto: {}", requestPaymentConfirmDto);
        log.info("requestPaymentConfirmDto.getPaymentUuid(): {}", requestPaymentConfirmDto.getPaymentUuid());
        // ✅ 결제 정보 갱신 (paymentCode, status)
        Payment payment = paymentRepository
                .findByPaymentUuid(requestPaymentConfirmDto.getPaymentUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PAYMENT_NO_EXIST));
        log.info("payment@@: {}", payment);
        // 결제 승인 처리가 이미 완료된 경우
        if (payment.getStatus() != PaymentStatus.READY) {
            // 이미 완료된 결제는 무시
            throw new BaseException(BaseResponseStatus.PAYMENT_ALREADY_DONE);
        }

        try {
            // 1. toss 통신 후 결제상태 갱신
            // Toss 결제 승인 api 요청 (postForEntity)
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    baseUrl + "/payments/confirm", httpRequest, Map.class
            );

            Map responseBody = response.getBody();
            log.info("responseBody@@: {}", responseBody);

            if (responseBody == null) {
                throw new BaseException(BaseResponseStatus.TOSS_EMPTY_RESPONSE);
            }

            String paymentCode = (String) responseBody.get("paymentKey");
            String paymentUuid = (String) responseBody.get("orderId");
            String method = (String) responseBody.get("method");
            Integer amount = (Integer) responseBody.get("totalAmount");
            PaymentStatus paymentStatus = PaymentStatus.valueOf((String) responseBody.get("status"));
            Map<String, String> failure = (Map<String, String>) responseBody.get("failure");
            // 가상 결제의 경우 approvedAt이 null일 수 있음
            LocalDateTime approvedAt = Optional.ofNullable((String) responseBody.get("approvedAt"))
                    .map(OffsetDateTime::parse)
                    .map(OffsetDateTime::toLocalDateTime)
                    .orElse(null);

            // 결제 실패 시 관련 정보 파싱 + 저장, 이후 에러 처리
            if (failure != null) {
//                String failureCode = failure.get("code");
                String failReason = failure.get("message");

                paymentRepository.save(requestPaymentConfirmDto.updateFailPayment(
                        payment, failReason
                ));
                throw new BaseException(BaseResponseStatus.PAYMENT_CONFIRM_FAIL);
            }

            // 금액 불일치 시
            if (!Objects.equals(amount, payment.getTotalPurchasePrice())) {
                throw new BaseException(BaseResponseStatus.PAYMENT_AMOUNT_MISMATCH);
            }
            // 결제 승인 성공 시 결제 상태 업데이트
            paymentRepository.save(requestPaymentConfirmDto.updateSuccessPayment(
                    payment, paymentCode, method, paymentStatus, approvedAt));

            // 2. 결제 승인 성공 시 주문 상태 업데이트 (여기서 1. 카트수정, 2. 재고감소, 3. best판매량추가 로직 처리)
            orderListService.updateOrderList(
                    requestPaymentConfirmDto.getUserUuid(),
                    payment.getOrderListUuid(),
                    paymentStatus.getDescription()
            );

            return ResponsePaymentConfirmDto.from(
                    paymentStatus.getDescription(),
                    paymentUuid,
                    paymentStatus,
                    approvedAt != null ? approvedAt.toString() : null,
                    method
            );
        } catch (Exception e) {
            // 결제 승인 실패 시 처리
            throw e;
        }
    }

    /**
     * 결제 상세 조회
     * @param paymentUuid
     */
    @Override
    public ResponsePaymentDto getPayment(String paymentUuid) {
        Payment payment = paymentRepository
                .findByPaymentUuid(paymentUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PAYMENT_NO_EXIST));

        return ResponsePaymentDto.from(payment);
    }

    /**
     * 결제 상태 업데이트
     */
    @Transactional
    @Override
    public void updatePaymentStatus(String paymentUuid, PaymentStatus status) {
        // 결제 상태가 '완료'가 아닐 경우 예외 처리
        if (!PaymentStatus.DONE.equals(status)) {
            throw new BaseException(BaseResponseStatus.VIRTUAL_PAYMENT_FAIL);
        }
        // 결제 UUID가 없으면 예외처리
        paymentRepository.findByPaymentUuid(paymentUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.PAYMENT_NO_EXIST));
        // Payment DB에 상태 저장
        LocalDateTime approvedAt = LocalDateTime.now();
        paymentRepository.updatePaymentStatus(paymentUuid, status, approvedAt);

        //
    }
}
