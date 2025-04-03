package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;
import com.starbucks.back.user.infrastructure.EmailSender;
import com.starbucks.back.user.infrastructure.EmailTemplateBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{
    private final RedisTemplate<String, String> redisTemplate;
    private final EmailSender emailSender;
    private final EmailTemplateBuilder templateBuilder;

    @Override
    public void sendEmailCode(RequestSendEmailCodeDto requestSendEmailCodeDto) {
        final String code = RandomStringUtils.random(6, true, true).toLowerCase();
        final String email = requestSendEmailCodeDto.getEmail();

        redisTemplate.opsForValue().set(
                email, // key
                code, //value
                5, // TTL : 유효시간
                TimeUnit.MINUTES // 시간 단위 : 분
        );

        emailSender.send(
                email,
                templateBuilder.buildVerificationEmail(code)
        );
    }
}
