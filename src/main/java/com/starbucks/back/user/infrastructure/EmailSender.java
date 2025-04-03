package com.starbucks.back.user.infrastructure;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class EmailSender {

    private final JavaMailSender javaMailSender;

    public void send(String to, String html) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(
                    "sbremail00@gmail.com",
                    "스타벅스 스토어",
                    "UTF-8"));
                helper.setTo(to);
            helper.setSubject("Starbucks 이메일 인증");
            helper.setText(html, true);
            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e ) {
            throw new BaseException(BaseResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

