package com.starbucks.back.user_withdrwal_pending.infrastructure;

import com.starbucks.back.user_withdrwal_pending.domain.UserWithdrawalPending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserWithdrawalPendingRepository extends JpaRepository<UserWithdrawalPending, Long> {
    List<UserWithdrawalPending> findByScheduledAt(LocalDate scheduledAt);
    void deleteByUserUuid(String userUuid);
}
