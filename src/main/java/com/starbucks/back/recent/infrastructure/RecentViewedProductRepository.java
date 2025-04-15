package com.starbucks.back.recent.infrastructure;

import com.starbucks.back.recent.domain.RecentViewedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentViewedProductRepository extends JpaRepository<RecentViewedProduct, Long> {
}
