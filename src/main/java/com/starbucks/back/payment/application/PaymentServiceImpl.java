package com.starbucks.back.payment.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.infrastructure.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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
        // paymentUuid 중복여부 검사
        if (paymentRepository.existsByPaymentUuid(requestPaymentCreateDto.getPaymentUuid())) {
            throw new BaseException(BaseResponseStatus.PAYMENT_DUPLICATE_ORDER_ID);
        }

        // 결제 내역 db 저장
        paymentRepository.save(requestPaymentCreateDto.toEntity());

        // 결제 생성 api 정보
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> body = new HashMap<>();

        body.put("orderId", requestPaymentCreateDto.getPaymentUuid());  // 결제 고유 ID
        body.put("amount", requestPaymentCreateDto.getTotalAmount());    // 실제 결제 금액
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

        // 결제 생성 결과 반환
        return ResponsePaymentCreateDto.builder()
                        .checkoutUrl(((Map<String, String>) responseBody.get("checkout")).get("url"))
                        .paymentUuid(requestPaymentCreateDto.getPaymentUuid())
                        .build();

    }
}
