package com.starbucks.back.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starbucks.back.common.entity.SoftDeletableEntity;
import com.starbucks.back.user.domain.enums.SignUpType;
import com.starbucks.back.user.domain.enums.UserGender;
import com.starbucks.back.user.domain.enums.UserState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends SoftDeletableEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "user_uuid", nullable = false, unique = true, length = 40, updatable = false)
    private String userUuid;

    @Column(name = "email", nullable = false, unique = true, updatable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 65)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "birthdate", nullable = false, length = 20)
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 20)
    private UserGender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 20)
    private UserState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private SignUpType type;


    @Builder
    public User(Long id, String userUuid, String email, String password, String nickname, String name,
                String phoneNumber, LocalDate birthdate, UserGender gender, UserState state, SignUpType type)
    {
        this.id = id;
        this.userUuid = userUuid;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.gender = gender;
        this.state = state;
        this.type = type;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.userUuid;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
