package com.starbucks.back.category.application;

import com.starbucks.back.category.dto.in.RequestCategoryDto;
import com.starbucks.back.category.dto.out.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {

    /**
     * 카테고리 추가
     * @param requestCategoryDto
     */
    void addCategory(RequestCategoryDto requestCategoryDto);

    /**
     * 카테고리 id로 카테고리 조회
     * @param id
     */
    ResponseCategoryDto getCategoryById(Long id);

    /**
     * 카테고리 이름으로 카테고리 조회
     * @param name
     */
    ResponseCategoryDto getCategoryByName(String name);

    /**
     * 카테고리 전체 조회
     */
    List<ResponseCategoryDto> getAllCategories();

    /**
     * 카테고리 수정
     * @param requestCategoryDto
     */
    void updateCategory(RequestCategoryDto requestCategoryDto);

    /**
     * 카테고리 삭제
     * @param requestCategoryDto
     */
    void deleteCategory(RequestCategoryDto requestCategoryDto);

}
