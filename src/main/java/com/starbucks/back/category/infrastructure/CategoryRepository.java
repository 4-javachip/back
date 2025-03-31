package com.starbucks.back.category.infrastructure;

import com.starbucks.back.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * 카테고리 이름으로 카테고리 찾기
     * @param name
     */
    Optional<Category> findByName(String name);

    /**
     * 카테고리 이름으로 삭제되지 않은 카테고리 찾기
     * @param name
     */
    Optional<Category> findByNameAndDeletedFalse(String name);

    /**
     * 삭제되지 않은 카테고리 전체 조회
     */
    List<Category> findAllByDeletedFalse();

    /**
     * 카테고리 이름으로 카테고리 존재 여부 확인
     * @param name
     */
    boolean existsByNameAndDeletedFalse(String name);

}
