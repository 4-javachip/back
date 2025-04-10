package com.starbucks.back.user.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.user.dto.enums.EmailVerificationPurpose;
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
    private final UserService userService;

    @Override
    public void sendEmailCode(RequestSendEmailCodeDto requestSendEmailCodeDto) {
        if (requestSendEmailCodeDto.getPurpose() == EmailVerificationPurpose.PASSWORD_RESET &&
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

        emailSender.send(email, templateBuilder.buildVerificationEmail(code));
        redisUtil.set("Fail:EmailVerify:" + email, "0", 5L, TimeUnit.MINUTES);
    }

    @Override
    public void verifyEmailCode(RequestVerificationEmailDto requestVerificationEmailDto) {
        final String email = requestVerificationEmailDto.getEmail();
        final String redisCode = redisUtil.get(email);

        if (redisCode == null) {
            throw new BaseException(BaseResponseStatus.EXPIRED_EMAIL_CODE);
        }

        if (!redisCode.equals(requestVerificationEmailDto.getVerificationCode())) {
            String failKey = "Fail:EmailVerify:" + email;

            if (redisUtil.increase(failKey, 5L, TimeUnit.MINUTES) >= 5) {
                redisUtil.delete(email);
                redisUtil.delete(failKey);
                throw new BaseException(BaseResponseStatus.EMAIL_CODE_VERIFICATION_LIMITED);
            }
            throw new BaseException(BaseResponseStatus.INVALID_EMAIL_CODE);
        }

        if (requestVerificationEmailDto.getPurpose() == EmailVerificationPurpose.PASSWORD_RESET) {
            redisUtil.set("PwdReset:Verified:" + email, "true", 10, TimeUnit.MINUTES);
        } else if (requestVerificationEmailDto.getPurpose() == EmailVerificationPurpose.SIGN_UP) {
            redisUtil.set("SignUp:Verified:" + email, "true", 20, TimeUnit.MINUTES);
        }

        redisUtil.delete(email);
        redisUtil.delete("Fail:EmailVerify:" + email);
    }
}
