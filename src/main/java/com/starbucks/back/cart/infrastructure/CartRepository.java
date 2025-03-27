package com.starbucks.back.cart.infrastructure;

import com.starbucks.back.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
