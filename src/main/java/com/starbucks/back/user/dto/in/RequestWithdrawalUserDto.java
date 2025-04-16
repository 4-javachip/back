package com.starbucks.back.user.dto.in;

import com.starbucks.back.user.domain.User;
import com.starbucks.back.user.domain.enums.UserState;
import com.starbucks.back.user.vo.in.RequestWithdrawalUserVo;
import com.starbucks.back.user_withdrwal_pending.domain.enums.WithdrawalReason;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestWithdrawalUserDto {
    private String userUuid;
    private WithdrawalReason reason;

    @Builder
    public RequestWithdrawalUserDto(String userUuid, WithdrawalReason reason) {
        this.userUuid = userUuid;
        this.reason = reason;
    }

    public static RequestWithdrawalUserDto of(String userUuid, RequestWithdrawalUserVo requestWithdrawalUserVo) {
        return RequestWithdrawalUserDto.builder()
                .userUuid(userUuid)
                .reason(WithdrawalReason.valueOf(requestWithdrawalUserVo.getReason().toUpperCase()))
                .build();
    }

    public User toEntity(User user) {
        return User.builder()
                .id(user.getId())
                .userUuid(user.getUserUuid())
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .birthdate(user.getBirthdate())
                .gender(user.getGender())
                .state(UserState.WITHDRAWAL_PENDING)
                .type(user.getType())
                .build();
    }
}
