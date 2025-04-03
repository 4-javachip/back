package com.starbucks.back.user.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.user.application.EmailService;
import com.starbucks.back.user.dto.in.RequestSendEmailCodeDto;
import com.starbucks.back.user.vo.in.RequestSendEmailCodeVo;
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

    @PostMapping("/send-code")
    public BaseResponseEntity<Void> sendEmailCode(@Valid @RequestBody RequestSendEmailCodeVo vo) {
        emailService.sendEmailCode(RequestSendEmailCodeDto.from(vo));
        return new BaseResponseEntity<>(BaseResponseStatus.EMAIL_CODE_SUCCESS);
    }

}
