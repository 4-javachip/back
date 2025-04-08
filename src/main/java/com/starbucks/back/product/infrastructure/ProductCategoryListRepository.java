package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.ProductCategoryList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryListRepository extends JpaRepository<ProductCategoryList, Long> {

    /**
     * 삭제되지 않은 상품 카테고리 리스트 전체 조회
     */
    List<ProductCategoryList> findAllByDeletedFalse();

    /**
     * 상품 UUID로 상품 카테고리 리스트 찾기
     * @param productUuid
     */
    Optional<ProductCategoryList> findByProductUuid(String productUuid);

    /**
     * 상품 UUID로 삭제되지 않은 상품 카테고리 리스트 찾기
     * @param productUuid
     */
    Optional<ProductCategoryList> findByProductUuidAndDeletedFalse(String productUuid);

}
