package com.starbucks.back.user_withdrwal_pending.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.BaseEntity;

import com.starbucks.back.user_withdrwal_pending.domain.enums.WithdrawalReason;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_withdrawal_pending")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserWithdrawalPending extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "user_uuid", nullable = false, unique = true, length = 40, updatable = false)
    private String userUuid;

    @Column(name = "request_at", nullable = false, updatable = false)
    private LocalDate requestAt;

    @Column(name = "scheduled_at", nullable = false, updatable = false)
    private LocalDate scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "reason", nullable = false, length = 20)
    private WithdrawalReason reason;

    @Builder
    public UserWithdrawalPending(Long id, String userUuid, LocalDate requestAt,
                                 LocalDate scheduledAt, WithdrawalReason reason) {
        this.id = id;
        this.userUuid = userUuid;
        this.requestAt = requestAt;
        this.scheduledAt = scheduledAt;
        this.reason = reason;
    }
}
