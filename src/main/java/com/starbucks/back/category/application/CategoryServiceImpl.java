package com.starbucks.back.category.application;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.dto.in.RequestCategoryDto;
import com.starbucks.back.category.dto.out.ResponseCategoryDto;
import com.starbucks.back.category.infrastructure.CategoryRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 추가
     * @param requestCategoryDto
     */
    @Transactional
    @Override
    public void addCategory(RequestCategoryDto requestCategoryDto) {
        if(categoryRepository.existsByNameAndDeletedFalse(requestCategoryDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY);
        }
        categoryRepository.save(requestCategoryDto.toEntity());
    }

    /**
     * id로 카테고리 조회
     * @param id
     */
    @Override
    public ResponseCategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        return ResponseCategoryDto.from(category);
    }

    /**
     * 카테고리 이름으로 카테고리 조회
     * @param name
     */
    @Override
    public ResponseCategoryDto getCategoryByName(String name) {
        Category category = categoryRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        return ResponseCategoryDto.from(category);
    }

    /**
     * 카테고리 전체 조회
     */
    @Override
    public List<ResponseCategoryDto> getAllCategories() {
        return categoryRepository.findAllByDeletedFalse().stream()
                .map(ResponseCategoryDto::from)
                .toList();
    }

    /**
     * 카테고리 수정
     * @param requestCategoryDto
     */
    @Transactional
    @Override
    public void updateCategory(RequestCategoryDto requestCategoryDto) {
        categoryRepository.save(requestCategoryDto.updateEntity());
    }

    /**
     * 카테고리 삭제
     * @param requestCategoryDto
     */
    @Transactional
    @Override
    public void deleteCategory(RequestCategoryDto requestCategoryDto) {
        Category category = categoryRepository.findById(requestCategoryDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        category.softDelete();
    }
}
