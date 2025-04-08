package com.starbucks.back.event.infrastructure;

import com.starbucks.back.event.domain.EventProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventProductRepository extends JpaRepository<EventProduct, Long>, EventProductCustomRepository {

    /**
     * 상품 uuid로 삭제되지 않은 기획전 상품 조회
     * @param productUuid
     */
    List<EventProduct> findByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 기획전 uuid, 상품 uuid로 삭제되지 않은 기획전 상품 존재 여부 조회
     * @param eventUuid
     * @param productUuid
     */
    boolean existsByEventUuidAndProductUuidAndDeletedFalse(String eventUuid, String productUuid);

}
