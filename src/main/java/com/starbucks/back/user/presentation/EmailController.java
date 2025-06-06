package com.starbucks.back.user.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.user.application.EmailService;
import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;
import com.starbucks.back.user.dto.in.RequestVerificationEmailDto;
import com.starbucks.back.user.vo.in.RequestSendEmailCodeVo;
import com.starbucks.back.user.vo.in.RequestVerificationEmailVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "Send Email Code API", description = "이메일 인증 코드 발송", tags = {"Email-service"})
    @PostMapping("/send-code")
    public BaseResponseEntity<Void> sendEmailCode(
            @Valid @RequestBody RequestSendEmailCodeVo requestSendEmailCodeVo
    ) {
        emailService.sendEmailCode(RequestSendEmailCodeDto.from(requestSendEmailCodeVo));
        return new BaseResponseEntity<>(BaseResponseStatus.EMAIL_CODE_SUCCESS);
    }

    @Operation(summary = "Verify Email Code API", description = "이메일 인증 코드 검증", tags = {"Email-service"})
    @PostMapping("/verify")
    public BaseResponseEntity<Void> verifyEmailCode(
            @Valid @RequestBody RequestVerificationEmailVo requestVerificationEmailVo
            ) {
        emailService.verifyEmailCode(RequestVerificationEmailDto.from(requestVerificationEmailVo));
        return new BaseResponseEntity<>(BaseResponseStatus.EMAIL_CODE_VERIFICATION_SUCCESS);
    }


}
