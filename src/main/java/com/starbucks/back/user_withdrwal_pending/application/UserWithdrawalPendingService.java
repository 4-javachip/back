package com.starbucks.back.user_withdrwal_pending.application;

import com.starbucks.back.user.dto.in.RequestWithdrawalUserDto;
import com.starbucks.back.user_withdrwal_pending.domain.UserWithdrawalPending;

import java.util.List;

public interface UserWithdrawalPendingService {
    void addWithdrawalPendingUser(RequestWithdrawalUserDto requestWithdrawalUserDto);
    void deleteWithdrawalPendingUser(String userUuid);
    List<UserWithdrawalPending> getWithdrawalPendingUsers();
    void recoveryAccount(String userUuid);
}
