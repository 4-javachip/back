package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    /**
     * 상품 UUID로 삭제되지 않은 상품 옵션 리스트 찾기
     * @param productUuid
     */
    List<ProductOption> findByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 상품 옵션 UUID로 상품 옵션 찾기
     * @param productOptionUuid
     */
    Optional<ProductOption> findByProductOptionUuid(String productOptionUuid);

}
