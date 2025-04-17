package com.starbucks.back.oauth.infrastructure;

import com.starbucks.back.oauth.domain.Oauth;
import com.starbucks.back.oauth.domain.enums.SocialProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OauthRepository extends JpaRepository<Oauth, Long> {
    Optional<Oauth> findByProviderAndProviderUserId(SocialProvider provider, String providerUserId);
    List<Oauth> findByUserUuid(String userUuid);
    Boolean existsByUserUuid(String userUuid);
    void deleteByUserUuid(String userUuid);
    @Modifying
    @Query("UPDATE Oauth o SET o.state = com.starbucks.back.oauth.domain.enums.OauthState.ACTIVE WHERE o.userUuid = :userUuid")
    void updateStateToActiveByUserUuid(@Param("userUuid") String userUuid);
}
