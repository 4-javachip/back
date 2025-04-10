package com.starbucks.back.auth.dto.in;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.domain.enums.SignUpType;
import com.starbucks.back.user.domain.enums.UserGender;
import com.starbucks.back.user.domain.enums.UserState;
import com.starbucks.back.auth.vo.in.RequestSignUpVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;

@Getter
public class RequestSignUpDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;
    @Builder
    public RequestSignUpDto(String email, String password, String name,
                            String nickname, String phoneNumber, LocalDate birthdate, UserGender gender)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
    }



    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .userUuid(randomUUID().toString())
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .birthdate(birthdate)
                .gender(gender)
                .state(UserState.ACTIVE)
                .type(SignUpType.GENERAL)
                .build();
    }

    public static RequestSignUpDto from(RequestSignUpVo requestSignUpVo) {
        return RequestSignUpDto.builder()
                .email(requestSignUpVo.getEmail())
                .password(requestSignUpVo.getPassword())
                .name(requestSignUpVo.getName())
                .nickname(requestSignUpVo.getNickname())
                .phoneNumber(requestSignUpVo.getPhoneNumber())
                .birthdate(requestSignUpVo.getBirthdate())
                .gender(requestSignUpVo.getGender())
                .build();
    }

}

