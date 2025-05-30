package com.starbucks.back.option.color.infrastructure;

import com.starbucks.back.option.color.domain.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {

    /**
     * 색상 이름으로 색상 찾기
     * @param name
     */
    Optional<Color> findByName(String name);

    /**
     * 색상 이름으로 색상 존재 여부 확인
     * @param name
     */
    boolean existsByName(String name);

}
