package com.starbucks.back.user_withdrwal_pending.application.scheduler;

import com.starbucks.back.user.application.UserService;
import com.starbucks.back.user_withdrwal_pending.application.UserWithdrawalPendingService;
import com.starbucks.back.user_withdrwal_pending.domain.UserWithdrawalPending;
import com.starbucks.back.user_withdrwal_pending.infrastructure.UserWithdrawalPendingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteWithdrawalPendingUserScheduler {

    private final UserWithdrawalPendingService userWithdrawalPendingService;

    @Scheduled(cron = "0 0 4 * * *") // 매일 새벽 4시 실행
    public void deleteExpiredWithdrawalUsers() {
        LocalDate today = LocalDate.now();
        List<UserWithdrawalPending> expired = userWithdrawalPendingService.getWithdrawalPendingUsers();

        if (expired.isEmpty()) {
            log.info("탈퇴 예정 유저 없음: {}", today);
            return;
        }

        log.info("탈퇴 예정 유저 {}명 삭제 시작", expired.size());

        expired.forEach(user -> {
            userWithdrawalPendingService.deleteWithdrawalPendingUser(user.getUserUuid());
            log.info("유저 {} 탈퇴 완료 및 데이터 삭제", user.getUserUuid());
        });

        log.info("탈퇴 예정 유저 삭제 완료");
    }
}