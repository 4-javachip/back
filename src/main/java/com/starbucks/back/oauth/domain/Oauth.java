package com.starbucks.back.oauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.BaseEntity;
import com.starbucks.back.oauth.domain.enums.SocialProvider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "oauth",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"provider", "provider_user_id"}),
        @UniqueConstraint(columnNames = {"user_uuid", "provider"})
    }
)
public class Oauth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "user_uuid", nullable = false)
    private String userUuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, length = 20)
    private SocialProvider provider;

    @Column(name = "provider_user_id", nullable = false, length = 100)
    private String providerUserId;

    @Builder
    public Oauth(Long id, String userUuid, SocialProvider provider, String providerUserId) {
        this.id = id;
        this.userUuid = userUuid;
        this.provider = provider;
        this.providerUserId = providerUserId;
    }
}

