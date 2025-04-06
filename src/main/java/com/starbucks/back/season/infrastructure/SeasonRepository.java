package com.starbucks.back.season.infrastructure;

import com.starbucks.back.season.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    /**
     * 시즌 이름으로 삭제되지 않은 시즌 찾기
     * @param name
     */
    Optional<Season> findByNameAndDeletedFalse(String name);

    /**
     * 삭제되지 않은 시즌 전체 조회
     */
    List<Season> findAllByDeletedFalse();

    /**
     * 시즌 이름으로 시즌 존재 여부 확인
     * @param name
     */
    boolean existsByNameAndDeletedFalse(String name);

}
