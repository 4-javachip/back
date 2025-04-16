package com.starbucks.back.oauth.dto.in;

import com.starbucks.back.oauth.vo.in.RequestOauthSignUpVo;
import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.domain.enums.SignUpType;
import com.starbucks.back.user.domain.enums.UserGender;
import com.starbucks.back.user.domain.enums.UserState;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static java.util.UUID.randomUUID;

@Getter
public class RequestOauthSignUpDto {
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String phoneNumber;
    private LocalDate birthdate;
    private UserGender gender;

    @Builder
    public RequestOauthSignUpDto (String email, String password, String name, String nickname,
                                  String phoneNumber, LocalDate birthdate, UserGender gender)
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
                .nickname(nickname)
                .name(name)
                .phoneNumber(phoneNumber)
                .birthdate(birthdate)
                .gender(gender)
                .state(UserState.ACTIVE)
                .type(SignUpType.SOCIAL)
                .build();
    }

    public static RequestOauthSignUpDto from(RequestOauthSignUpVo requestOauthSignUpVo) {
        return RequestOauthSignUpDto.builder()
                .email(requestOauthSignUpVo.getEmail())
                .password(requestOauthSignUpVo.getPassword())
                .nickname(requestOauthSignUpVo.getNickname())
                .name(requestOauthSignUpVo.getName())
                .phoneNumber(requestOauthSignUpVo.getPhoneNumber())
                .birthdate(requestOauthSignUpVo.getBirthdate())
                .gender(requestOauthSignUpVo.getGender())
                .build();
    }

}
