package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.in.RequestSignUpDto;

public interface UserService {
    void signUp(RequestSignUpDto requestSignUpDto);
    boolean existsEmail(String email);
    boolean existsNickname(String nickname);


}
