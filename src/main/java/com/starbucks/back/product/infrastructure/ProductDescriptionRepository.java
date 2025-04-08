package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long> {

    /**
     * 상품 UUID로 상품 설명 찾기
     * @param productUuid
     */
    Optional<ProductDescription> findByProductUuid(String productUuid);



}
