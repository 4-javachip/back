package com.starbucks.back.user_withdrwal_pending.application;

import com.starbucks.back.oauth.infrastructure.OauthRepository;
import com.starbucks.back.user.dto.in.RequestWithdrawalUserDto;
import com.starbucks.back.user.infrastructure.UserRepository;
import com.starbucks.back.user_withdrwal_pending.domain.UserWithdrawalPending;
import com.starbucks.back.user_withdrwal_pending.infrastructure.UserWithdrawalPendingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserWithdrawalPendingServiceImpl implements UserWithdrawalPendingService {

    private final UserWithdrawalPendingRepository userWithdrawalPendingRepository;
    private final UserRepository userRepository;
    private final OauthRepository oauthRepository;

    @Transactional
    @Override
    public void addWithdrawalPendingUser(RequestWithdrawalUserDto requestWithdrawalUserDto) {
        final LocalDate now = LocalDate.now();
        final LocalDate scheduled = now.plusDays(14);

        userWithdrawalPendingRepository.save(
                UserWithdrawalPending.builder()
                        .userUuid(requestWithdrawalUserDto.getUserUuid())
                        .requestAt(now)
                        .scheduledAt(scheduled)
                        .reason(requestWithdrawalUserDto.getReason())
                        .build()
        );
    }

    @Transactional
    @Override
    public void deleteWithdrawalPendingUser(String userUuid) {
        userRepository.deleteByUserUuid(userUuid);
        oauthRepository.deleteByUserUuid(userUuid);
        userWithdrawalPendingRepository.deleteByUserUuid(userUuid);
    }

    @Override
    public List<UserWithdrawalPending> getWithdrawalPendingUsers() {
        return userWithdrawalPendingRepository.findByScheduledAt(LocalDate.now());
    }

    public void recoveryAccount(String userUuid) {

    }

}
