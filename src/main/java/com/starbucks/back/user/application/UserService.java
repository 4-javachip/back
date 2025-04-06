package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.out.ResponseGetUserInfoDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String userUuid);
    ResponseGetUserInfoDto getUserInfo(String userUuid);
}
