package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * 상품 이름으로 상품 찾기
     * @param name
     */
    Optional<Product> findByName(String name);

    /**
     * 상품 이름으로 삭제되지 않은 상품 찾기
     * @param name
     */
    Optional<Product> findByNameAndDeletedFalse(String name);

    /**
     * 삭제되지 않은 상품 전체 조회
     */
    List<Product> findAllByDeletedFalse();

    /**
     * 상품 UUID로 삭제되지 않은 상품 찾기
     * @param productUuid
     */
    Optional<Product> findByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 상품 이름으로 상품 존재 여부 확인
     * @param name
     */
    boolean existsByNameAndDeletedFalse(String name);

}
