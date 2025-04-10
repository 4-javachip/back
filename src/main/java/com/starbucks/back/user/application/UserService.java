package com.starbucks.back.user.application;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.dto.in.RequestMatchPasswordDto;
import com.starbucks.back.user.dto.in.RequestResetPasswordDto;
import com.starbucks.back.user.dto.in.RequestUpdateNicknameDto;
import com.starbucks.back.user.dto.in.RequestUpdatePasswordDto;
import com.starbucks.back.user.dto.out.ResponseGetUserInfoDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    UserDetails loadUserByUsername(String userUuid);
    User loadUserByEmail(String email);
    ResponseGetUserInfoDto getUserInfo(String userUuid);
    void authenticateCurrentPassword(RequestMatchPasswordDto requestMatchPasswordDto);
    void updatePassword(RequestUpdatePasswordDto requestUpdatePasswordDto);
    void resetPassword(RequestResetPasswordDto requestResetPasswordDto);
    void updateNickname(RequestUpdateNicknameDto requestUpdateNicknameDto);
}
