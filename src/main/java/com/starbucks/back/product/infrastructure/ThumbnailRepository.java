package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {

    /**
     * 삭제되지 않은 썸네일 productUuid로 찾기
     * @param productUuid
     */
    Optional<Thumbnail> findByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 삭제되지 않은 썸네일 전체 조회
     */
    List<Thumbnail> findAllByDeletedFalse();

}
