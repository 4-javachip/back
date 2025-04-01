package com.starbucks.back.user.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.user.dto.in.RequestSignUpDto;
import com.starbucks.back.user.infrastructure.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void signUp(RequestSignUpDto requestSignUpDto) {
        if(userRepository.existsByEmail(requestSignUpDto.getEmail())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        } else if (userRepository.existsByNickname(requestSignUpDto.getNickname())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_NICKNAME);
        } else if (userRepository.existsByPhoneNumber(requestSignUpDto.getPhoneNumber())){
            throw new BaseException(BaseResponseStatus.DUPLICATED_PHONE_NUMBER);
        }

        userRepository.save(requestSignUpDto.toEntity(passwordEncoder));
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
