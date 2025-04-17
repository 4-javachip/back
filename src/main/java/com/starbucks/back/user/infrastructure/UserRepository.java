package com.starbucks.back.user.infrastructure;

import com.starbucks.back.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User> findByUserUuid(String uuid);
    void deleteByUserUuid(String userUuid);
    @Modifying
    @Query("UPDATE User u SET u.state = com.starbucks.back.user.domain.enums.UserState.ACTIVE WHERE u.userUuid = :userUuid")
    void updateStateToActiveByUserUuid(@Param("userUuid") String userUuid);

}
