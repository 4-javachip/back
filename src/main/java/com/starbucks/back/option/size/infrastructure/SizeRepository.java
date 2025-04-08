package com.starbucks.back.option.size.infrastructure;

import com.starbucks.back.option.size.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<Size, Long> {

    /**
     * 사이즈 이름으로 사이즈 찾기
     * @param name
     */
    Optional<Size> findByName(String name);

    /**
     * 사이즈 이름으로 사이즈 존재 여부 확인
     * @param name
     */
    boolean existsByName(String name);

}
