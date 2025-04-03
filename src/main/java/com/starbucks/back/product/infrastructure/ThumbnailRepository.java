package com.starbucks.back.product.infrastructure;

import com.starbucks.back.product.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {

    /**
     * 썸네일 id로 삭제되지 않은 썸네일 조회
     * @param id
     */
    Optional<Thumbnail> findByIdAndDeletedFalse(Long id);

    /**
     * 상품 UUID로 삭제되지 않은 썸네일 조회
     * @param productUuid
     */
    List<Thumbnail> findAllByProductUuidAndDeletedFalseOrderByDefaultedDescIdAsc(String productUuid);

    /**
     * 삭제되지 않은 메인 이미지 전체 조회
     */
    List<Thumbnail> findAllByDeletedFalseAndDefaultedTrue();

    /**
     * 삭제되지 않은 썸네일 전체 조회
     */
    List<Thumbnail> findAllByDeletedFalse();

}
