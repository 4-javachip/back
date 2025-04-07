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
     * 카테고리 아이디로 서브 카테고리 리스트 찾기
     * @param categoryId
     */
    List<SubCategory> findByCategoryId(Long categoryId);

    /**
     * 같은 카테고리 내 서브 카테고리 이름 존재 여부 확인
     * @param name
     */
    boolean existsByCategoryIdAndName(Long categoryId, String name);

}
