package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.vo.in.RequestUpdatePasswordVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class RequestUpdatePasswordDto {
    private String userUuid;
    private String newPassword;
    private String confirmPassword;

    @Builder
    public RequestUpdatePasswordDto(String userUuid ,String newPassword, String confirmPassword) {
        this.userUuid = userUuid;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public User toEntity(User user, String newPassword,PasswordEncoder passwordEncoder) {
        return User.builder()
                .id(user.getId())
                .userUuid(user.getUserUuid())
                .email(user.getEmail())
                .password(passwordEncoder.encode(newPassword))
                .nickname(user.getNickname())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .state(user.getState())
                .type(user.getType())
                .build();
    }

    public static RequestUpdatePasswordDto of(String userUuid, RequestUpdatePasswordVo requestUpdatePasswordVo) {
        return RequestUpdatePasswordDto.builder()
                .userUuid(userUuid)
                .newPassword(requestUpdatePasswordVo.getNewPassword())
                .confirmPassword(requestUpdatePasswordVo.getConfirmPassword())
                .build();
    }
}
