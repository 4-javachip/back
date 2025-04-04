package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSignUpDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    void signUp(RequestSignUpDto requestSignUpDto);
    boolean existsEmail(String email);
    boolean existsNickname(String nickname);

    UserDetails loadUserByUsername(String userUuid);
}
