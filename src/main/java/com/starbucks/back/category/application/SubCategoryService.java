package com.starbucks.back.category.application;

import com.starbucks.back.category.dto.in.*;
import com.starbucks.back.category.dto.out.ResponseSubCategoryDto;

import java.util.List;

public interface SubCategoryService {

    /**
     * 서브 카테고리 추가
     * @param requestAddSubCategoryDto
     */
    void addSubCategory(RequestAddSubCategoryDto requestAddSubCategoryDto);

    /**
     * 서브 카테고리 id로 서브 카테고리 조회
     * @param id
     */
    ResponseSubCategoryDto getSubCategoryById(Long id);

    /**
     * 서브 카테고리 이름으로 서브 카테고리 조회
     * @param name
     */
    ResponseSubCategoryDto getSubCategoryByName(String name);

    /**
     * 서브 카테고리 전체 조회
     */
    List<ResponseSubCategoryDto> getAllSubCategories();

    /**
     * 서브 카테고리 수정
     * @param requestUpdateSubCategoryDto
     */
    void updateSubCategory(RequestUpdateSubCategoryDto requestUpdateSubCategoryDto);

    /**
     * 서브 카테고리 삭제
     * @param requestDeleteSubCategoryDto
     */
    void deleteSubCategory(RequestDeleteSubCategoryDto requestDeleteSubCategoryDto);

}
