package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;

public interface EmailService {
    void sendEmailCode(RequestSendEmailCodeDto dto);
}
