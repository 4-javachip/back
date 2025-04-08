package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestMatchPasswordDto;
import com.starbucks.back.user.dto.in.RequestResetPasswordDto;
import com.starbucks.back.user.dto.in.RequestUpdateNicknameDto;
import com.starbucks.back.user.dto.in.RequestUpdatePasswordDto;
import com.starbucks.back.user.dto.out.ResponseGetUserInfoDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String userUuid);
    ResponseGetUserInfoDto getUserInfo(String userUuid);
    void authenticateCurrentPassword(RequestMatchPasswordDto requestMatchPasswordDto);
    void updatePassword(RequestUpdatePasswordDto requestUpdatePasswordDto);
    void resetPassword(RequestResetPasswordDto requestResetPasswordDto);
    void updateNickname(RequestUpdateNicknameDto requestUpdateNicknameDto);
}
