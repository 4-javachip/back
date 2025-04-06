package com.starbucks.back.user.dto.out;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.domain.enums.UserGender;
import com.starbucks.back.user.vo.out.ResponseGetUserInfoVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResponseGetUserInfoDto {
    private String userUuid;
    private String email;
    private String nickname;
    private String name;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;

    @Builder
    public ResponseGetUserInfoDto (
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

    public static ResponseGetUserInfoDto from(User user) {
        return ResponseGetUserInfoDto.builder()
                .userUuid(user.getUserUuid())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .build();
    }

    public ResponseGetUserInfoVo toVo(){
        return ResponseGetUserInfoVo.builder()
                .userUuid(userUuid)
                .email(email)
                .nickname(nickname)
                .name(name)
                .phoneNumber(phoneNumber)
                .birthdate(birthdate)
                .gender(gender)
                .build();
    }


}
