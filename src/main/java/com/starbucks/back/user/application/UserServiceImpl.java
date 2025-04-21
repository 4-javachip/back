package com.starbucks.back.user.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.RedisUtil;
import com.starbucks.back.oauth.application.OauthService;
import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.dto.in.*;
import com.starbucks.back.user.dto.out.ResponseGetUserInfoDto;
import com.starbucks.back.user.infrastructure.UserRepository;
import com.starbucks.back.user_withdrwal_pending.application.UserWithdrawalPendingService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

import static com.starbucks.back.common.entity.BaseResponseStatus.*;


@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserWithdrawalPendingService userWithdrawalPendingService;
    private final OauthService oauthService;
    private final RedisUtil<String> redisUtil;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserWithdrawalPendingService userWithdrawalPendingService, OauthService oauthService, RedisUtil<String> redisUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userWithdrawalPendingService = userWithdrawalPendingService;
        this.oauthService = oauthService;
        this.redisUtil = redisUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userUuid) {
        return userRepository.findByUserUuid(userUuid).orElseThrow(() -> new IllegalArgumentException(userUuid));
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public ResponseGetUserInfoDto getUserInfo(String userUuid) {
        return ResponseGetUserInfoDto.from(
                userRepository.findByUserUuid(userUuid).orElseThrow(
                        () -> new IllegalArgumentException(userUuid)
                )
        );
    }

    @Transactional
    @Override
    public void authenticateCurrentPassword(RequestMatchPasswordDto requestMatchPasswordDto) {
        User user = userRepository.findByUserUuid(requestMatchPasswordDto.getUserUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

        if (!passwordEncoder.matches(requestMatchPasswordDto.getCurrentPassword(), user.getPassword())) {
            throw new BaseException(BaseResponseStatus.PASSWORD_MATCH_FAILED);
        }

        switch (requestMatchPasswordDto.getPurpose()) {
            case PASSWORD_UPDATE:
                redisUtil.set("PwdChange:Verified:" + requestMatchPasswordDto.getUserUuid(), "true", 10, TimeUnit.MINUTES);
                break;
            case WITHDRAWAL:
                redisUtil.set("Withdrawal:Verified:" + requestMatchPasswordDto.getUserUuid(), "true", 5, TimeUnit.MINUTES);
                break;
            default:
                throw new BaseException(BaseResponseStatus.INVALID_PURPOSE);
        }
    }

    @Transactional
    @Override
    public void updatePassword(RequestUpdatePasswordDto requestUpdatePasswordDto) {
        if (!requestUpdatePasswordDto.getNewPassword()
                .equals(requestUpdatePasswordDto.getConfirmPassword())
        ) {
            throw new BaseException(PASSWORD_CONFIRM_MISMATCH);
        }

        if (!"true".equals(redisUtil.get("PwdChange:Verified:" + requestUpdatePasswordDto.getUserUuid()))) {
            throw new BaseException(PASSWORD_CHANGE_NOT_VERIFIED);
        }

        userRepository.save(
                requestUpdatePasswordDto.toEntity(
                        userRepository.findByUserUuid(
                                requestUpdatePasswordDto.getUserUuid())
                                .orElseThrow(() -> new BaseException(USER_NOT_FOUND)),
                        requestUpdatePasswordDto.getNewPassword(),
                        passwordEncoder
                )
        );

        redisUtil.delete("PwdChange:Verified:" + requestUpdatePasswordDto.getUserUuid());
    }

    @Transactional
    @Override
    public void resetPassword(RequestResetPasswordDto requestResetPasswordDto) {
        if (!requestResetPasswordDto.getNewPassword()
                .equals(requestResetPasswordDto.getConfirmPassword())
        ) {
            throw new BaseException(PASSWORD_CONFIRM_MISMATCH);
        }

        if (!"true".equals(redisUtil.get("PwdReset:Verified:" + requestResetPasswordDto.getEmail()))) {
            throw new BaseException(PASSWORD_CHANGE_NOT_VERIFIED);
        }

        userRepository.save(
                requestResetPasswordDto.toEntity(
                        userRepository.findByEmail(
                                requestResetPasswordDto.getEmail())
                                .orElseThrow(() -> new BaseException(USER_NOT_FOUND)),
                        requestResetPasswordDto.getNewPassword(),
                        passwordEncoder
                )
        );

        redisUtil.delete("PwdChange:Verified:" + requestResetPasswordDto.getEmail());
    }

    @Transactional
    @Override
    public void updateNickname(RequestUpdateNicknameDto requestUpdateNicknameDto) {
        userRepository.save(
                requestUpdateNicknameDto.toEntity(
                        userRepository.findByUserUuid(requestUpdateNicknameDto.getUserUuid())
                        .orElseThrow(() -> new BaseException(USER_NOT_FOUND)),
                        requestUpdateNicknameDto
                )
        );
    }

    @Transactional
    @Override
    public void withdrawalUser(RequestWithdrawalUserDto requestWithdrawalUserDto) {
        User user = userRepository.findByUserUuid(requestWithdrawalUserDto.getUserUuid())
                .orElseThrow(() -> new BaseException(USER_NOT_FOUND));

        if (!"true".equals(redisUtil.get("Withdrawal:Verified:" + requestWithdrawalUserDto.getUserUuid()))) {
            throw new BaseException(BaseResponseStatus.WITHDRAWAL_NOT_VERIFIED);
        }

        userRepository.save(requestWithdrawalUserDto.toEntity(user));

        if (oauthService.existsOauth(requestWithdrawalUserDto.getUserUuid())) {
            oauthService.withdrawalPendingOauth(requestWithdrawalUserDto.getUserUuid());
        }
        userWithdrawalPendingService.addWithdrawalPendingUser(requestWithdrawalUserDto);

        redisUtil.delete("Withdrawal:Verified:" + requestWithdrawalUserDto.getUserUuid());
    }

    @Override
    public void accountRecovery(RequestAccountRecoveryDto requestAccountRecoveryDto) {
        userWithdrawalPendingService.recoveryAccount(requestAccountRecoveryDto.getEmail());
    }

}
