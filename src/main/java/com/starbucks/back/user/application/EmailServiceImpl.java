package com.starbucks.back.user.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.user.dto.enums.SendEmailPurpose;
import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;
import com.starbucks.back.user.dto.in.RequestVerificationEmailDto;
import com.starbucks.back.user.infrastructure.EmailSender;
import com.starbucks.back.user.infrastructure.template.EmailTemplateBuilder;
import com.starbucks.back.user_withdrwal_pending.application.UserWithdrawalPendingService;
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
    private final UserService userService;
    private final UserWithdrawalPendingService userWithdrawalPendingService;

    @Override
    public void sendEmailCode(RequestSendEmailCodeDto requestSendEmailCodeDto) {
        if (requestSendEmailCodeDto.getPurpose() == SendEmailPurpose.PASSWORD_RESET &&
                userService.loadUserByEmail(requestSendEmailCodeDto.getEmail()) == null
        ) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_EMAIL);
        }

        final String code = RandomStringUtils.random(6, true, true);
        final String email = requestSendEmailCodeDto.getEmail();

        final String limitKey = "Limit:EmailSend:" + email;
        if (redisUtil.get(limitKey) != null) {
            throw new BaseException(BaseResponseStatus.EMAIL_CODE_SEND_LIMITED);
        }


        redisUtil.set(email, code, 5L, TimeUnit.MINUTES);
        redisUtil.set(limitKey, "3", 3L, TimeUnit.MINUTES);

        if (requestSendEmailCodeDto.getPurpose() == SendEmailPurpose.SIGN_UP |
        requestSendEmailCodeDto.getPurpose() == SendEmailPurpose.PASSWORD_RESET) {
            emailSender.send(email, "Starbucks 이메일 인증", templateBuilder.buildVerificationEmail("이메일 인증을", code));
        }
        else {
            emailSender.send(email, "Starbucks 계정 복구 인증", templateBuilder.buildVerificationEmail("계정 복구를", code));
        }

        redisUtil.set("EmailVerify:" + email, "0", 5L, TimeUnit.MINUTES);

    }

    @Override
    public void verifyEmailCode(RequestVerificationEmailDto requestVerificationEmailDto) {
        final String email = requestVerificationEmailDto.getEmail();
        final String redisCode = redisUtil.get(email);

        if (redisCode == null) {
            throw new BaseException(BaseResponseStatus.EXPIRED_EMAIL_CODE);
        }

        if (!redisCode.equals(requestVerificationEmailDto.getVerificationCode())) {
            String failKey = "EmailVerify:" + email;

            if (redisUtil.increase(failKey, 5L, TimeUnit.MINUTES) >= 5) {
                redisUtil.delete(email);
                redisUtil.delete(failKey);
                throw new BaseException(BaseResponseStatus.EMAIL_CODE_VERIFICATION_LIMITED);
            }
            throw new BaseException(BaseResponseStatus.INVALID_EMAIL_CODE);
        }

        if (requestVerificationEmailDto.getPurpose() == SendEmailPurpose.PASSWORD_RESET) {
            redisUtil.set("PwdReset:Verified:" + email, "true", 10, TimeUnit.MINUTES);
        } else if (requestVerificationEmailDto.getPurpose() == SendEmailPurpose.SIGN_UP) {
            redisUtil.set("SignUp:Verified:" + email, "true", 20, TimeUnit.MINUTES);
        } else {
            redisUtil.set("AccountRecovery:Verified:" + email, "true", 10, TimeUnit.MINUTES);
        }

        redisUtil.delete(email);
        redisUtil.delete("EmailVerify:" + email);
    }
}
