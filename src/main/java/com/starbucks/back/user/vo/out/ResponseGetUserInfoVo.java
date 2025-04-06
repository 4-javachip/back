package com.starbucks.back.user.vo.out;

import com.starbucks.back.user.domain.enums.UserGender;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResponseGetUserInfoVo {
    private String userUuid;
    private String email;
    private String nickname;
    private String name;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;

    @Builder
    public ResponseGetUserInfoVo (
            String userUuid,
            String email,
            String nickname,
            String name,
            String phoneNumber,
            LocalDate birthdate,
            UserGender gender
    ){
        this.userUuid = userUuid;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
    }

}
