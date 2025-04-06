package com.starbucks.back.user.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;
import com.starbucks.back.user.dto.in.RequestVerificationEmailDto;
import com.starbucks.back.user.infrastructure.EmailSender;
import com.starbucks.back.user.infrastructure.template.EmailTemplateBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final RedisUtil<String> redisUtil;
    private final EmailSender emailSender;
    private final EmailTemplateBuilder templateBuilder;

    @Override
    public void sendEmailCode(RequestSendEmailCodeDto requestSendEmailCodeDto) {
        final String code = RandomStringUtils.random(6, true, true).toLowerCase();
        final String email = requestSendEmailCodeDto.getEmail();

        redisUtil.set(
                email,
                code,
                5L,
                TimeUnit.MINUTES
        );

        emailSender.send(
                email,
                templateBuilder.buildVerificationEmail(code)
        );
    }

    @Override
    public void verifyEmailCode(RequestVerificationEmailDto requestVerificationEmailDto) {
        final String email = requestVerificationEmailDto.getEmail();
        final String redisCode = redisUtil.get(email);

        if (redisCode == null) {
            throw new BaseException(BaseResponseStatus.EXPIRED_EMAIL_CODE);
        }

        if (!redisCode.equals(requestVerificationEmailDto.getVerificationCode())) {
            throw new BaseException(BaseResponseStatus.INVALID_EMAIL_CODE);
        }

        redisUtil.delete(email);
    }
}
