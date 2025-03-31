package com.starbucks.back.category.infrastructure;

import com.starbucks.back.category.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    /**
     * 서브 카테고리 이름으로 서브 카테고리 찾기
     * @param name
     */
    Optional<SubCategory> findByName(String name);

    /**
     * 서브 카테고리 이름으로 삭제되지 않은 서브 카테고리 찾기
     * @param name
     */
    Optional<SubCategory> findByNameAndDeletedFalse(String name);

    /**
     * 삭제되지 않은 서브 카테고리 전체 조회
     */
    List<SubCategory> findAllByDeletedFalse();

    /**
     * 서브 카테고리 이름으로 서브 카테고리 존재 여부 확인
     * @param name
     */
    boolean existsByNameAndDeletedFalse(String name);

}
