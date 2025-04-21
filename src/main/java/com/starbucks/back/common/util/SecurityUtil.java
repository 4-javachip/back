package com.starbucks.back.common.util;

import com.starbucks.back.user.domain.User;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtil {

    public String getCurrentUserUuid() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserDetails getCurrentUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getCurrentUserNickname() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails instanceof User user) {
            return user.getNickname();
        }
        throw new IllegalStateException("UserDetails가 User 타입이 아님");
    }

}
