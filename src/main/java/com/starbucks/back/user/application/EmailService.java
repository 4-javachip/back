package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;
import com.starbucks.back.user.dto.in.RequestVerificationEmailDto;

public interface EmailService {
    void sendEmailCode(RequestSendEmailCodeDto requestSendEmailCodeDto);
    void verifyEmailCode(RequestVerificationEmailDto requestVerificationEmailDto);
}
