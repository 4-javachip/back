package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.ProductDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription, Long> {

    /**
     * 삭제되지 않은 상품 설명 전체 조회
     */
    List<ProductDescription> findAllByDeletedFalse();

    /**
     * 상품 UUID로 삭제되지 않은 상품 설명 찾기
     * @param productUuid
     */
    Optional<ProductDescription> findByProductUuidAndDeletedFalse(String productUuid);



}
