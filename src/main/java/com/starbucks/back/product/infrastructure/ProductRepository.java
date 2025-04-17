package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.Product;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCustomRepository {

    /**
     * 상품 이름으로 상품 찾기
     * @param name
     */
    Optional<Product> findByName(String name);

    /**
     * 상품 UUID로 상품 찾기
     * @param productUuid
     */
    Optional<Product> findByProductUuid(String productUuid);

    /**
     * 상품 이름으로 상품 존재 여부 확인
     * @param name
     */
    boolean existsByName(String name);

    /**
     * product table에 best 컬럼 전부 false 처리
     */
    @Modifying
    @Query("update Product p set p.best = false")
    void updateAllBestFalse();

    /**
     * 상품 UUID로 베스트 상품 업데이트
     * @param uuids
     */
    @Modifying
    @Query("update Product p set p.best = true where p.productUuid in :uuids")
    void updateBestTrueByProductUuids(@Param("uuids") Set<String> uuids);

}
