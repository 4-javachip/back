package com.starbucks.back.agreement.presentation;

import com.starbucks.back.agreement.application.UserAgreementService;
import com.starbucks.back.agreement.dto.in.RequestAddUserAgreementDto;
import com.starbucks.back.agreement.vo.in.RequestAddUserAgreementVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-agreement")
@RequiredArgsConstructor
public class UserAgreementController {

    private final UserAgreementService userAgreementService;

    @PostMapping
    public BaseResponseEntity<Void> addUserAgreement(@RequestBody RequestAddUserAgreementVo requestAddUserAgreementVo) {
        userAgreementService.addUserAgreement(RequestAddUserAgreementDto.from(requestAddUserAgreementVo));
        return new BaseResponseEntity<>();
    }


}
