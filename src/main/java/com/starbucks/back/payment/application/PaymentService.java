package com.starbucks.back.payment.application;

import com.starbucks.back.payment.domain.PaymentStatus;
import com.starbucks.back.payment.dto.in.RequestPaymentConfirmDto;
import com.starbucks.back.payment.dto.in.RequestPaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentConfirmDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentCreateDto;
import com.starbucks.back.payment.dto.out.ResponsePaymentDto;

public interface PaymentService {
    ResponsePaymentCreateDto addPayment(RequestPaymentCreateDto requestPaymentCreateDto);
    ResponsePaymentConfirmDto confirmPayment(RequestPaymentConfirmDto requestPaymentConfirmDto);
    ResponsePaymentDto getPayment(String paymentUuid);
    void updatePaymentStatus(String paymentUuid, PaymentStatus status);
}
