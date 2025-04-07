package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {

    /**
     * 상품 UUID로 삭제되지 않은 썸네일 조회
     * @param productUuid
     */
    List<Thumbnail> findAllByProductUuidAndDeletedFalseOrderByDefaultedDescIdAsc(String productUuid);

}
