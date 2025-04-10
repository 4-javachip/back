package com.starbucks.back.best.infrastructure;

import com.starbucks.back.best.domain.Best;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BestRepository extends JpaRepository<Best, Long>, BestCustomRepository {

    /**
     * 베스트 상품 전체 조회(상위 30개)
     */
    List<Best> findTop30ByOrderByProductSalesCountDesc();

    /**
     * 상품 UUID로 베스트 상품 찾기
     * @param productUuid
     */
    Optional<Best> findByProductUuid(String productUuid);

}
