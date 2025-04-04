package com.starbucks.back.season.infrastructure;

import com.starbucks.back.season.domain.SeasonList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeasonListRepository extends JpaRepository<SeasonList, Long> {

    /**
     * 시즌 ID로 삭제되지 않은 시즌 리스트 조회
     * @param seasonId
     */
    List<SeasonList> findBySeasonIdAndDeletedFalse(Long seasonId);

    /**
     * 상품 UUID로 삭제되지 않은 시즌 리스트 조회
     * @param productUuid
     */
    Optional<SeasonList> findByProductUuidAndDeletedFalse(String productUuid);

    /**
     * 삭제되지 않은 시즌 리스트 전체 조회
     */
    List<SeasonList> findAllByDeletedFalse();

}
