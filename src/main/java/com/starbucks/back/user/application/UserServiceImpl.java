package com.starbucks.back.user.application;

import com.starbucks.back.user.dto.out.ResponseGetUserInfoDto;
import com.starbucks.back.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userUuid) {
        return userRepository.findByUserUuid(userUuid).orElseThrow(() -> new IllegalArgumentException(userUuid));
    }

    @Override
    public ResponseGetUserInfoDto getUserInfo(String userUuid) {
        return ResponseGetUserInfoDto.from(
                userRepository.findByUserUuid(userUuid).orElseThrow(
                        () -> new IllegalArgumentException(userUuid)
                )
        );
    }

}
