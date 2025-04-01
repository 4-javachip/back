package com.starbucks.back.agreement.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agreement_id", nullable = false)
    private Agreement agreement;

    @Column(name = "agreed", nullable = false)
    private Boolean agreed;

    @Column(name = "user_uuid", nullable = false, length = 40)
    private String userUuid;

    @Column(name = "user_agreement_uuid", nullable = false, length = 40)
    private String userAgreementUuid;

    @Builder
    public UserAgreement(Long id, Agreement agreement, Boolean agreed, String userUuid, String userAgreementUuid) {
        this.id = id;
        this.agreement = agreement;
        this.agreed = agreed;
        this.userUuid = userUuid;
        this.userAgreementUuid = userAgreementUuid;
    }
}
