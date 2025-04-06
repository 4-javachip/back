package com.starbucks.back.auth.dto.in;

import com.starbucks.back.auth.vo.in.RequestSignInVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestSignInDto {
    private String email;
    private String password;

    @Builder
    public RequestSignInDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static RequestSignInDto from(RequestSignInVo requestSignInVo) {
        return RequestSignInDto.builder()
                .email(requestSignInVo.getEmail())
                .password(requestSignInVo.getPassword())
                .build();
    }
}
