package com.starbucks.back.recentviewed.infrastructure;

import com.starbucks.back.recentviewed.domain.RecentViewedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentViewedProductRepository extends JpaRepository<RecentViewedProduct, Long> {
}
