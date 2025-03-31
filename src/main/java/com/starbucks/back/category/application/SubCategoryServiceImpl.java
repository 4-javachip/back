package com.starbucks.back.category.application;

import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.domain.SubCategory;
import com.starbucks.back.category.dto.in.*;
import com.starbucks.back.category.dto.out.ResponseSubCategoryDto;
import com.starbucks.back.category.infrastructure.CategoryRepository;
import com.starbucks.back.category.infrastructure.SubCategoryRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 서브 카테고리 추가
     *
     * @param requestAddSubCategoryDto
     */
    @Transactional
    @Override
    public void addSubCategory(RequestAddSubCategoryDto requestAddSubCategoryDto) {
        Category category = categoryRepository.findById(requestAddSubCategoryDto.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        if (subCategoryRepository.existsByNameAndDeletedFalse(requestAddSubCategoryDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_CATEGORY);
        }
        SubCategory subCategory = requestAddSubCategoryDto.toEntity(category);
        subCategoryRepository.save(subCategory);
    }

    /**
     * id로 서브 카테고리 조회
     *
     * @param id
     */
    @Override
    public ResponseSubCategoryDto getSubCategoryById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        return ResponseSubCategoryDto.from(subCategory);
    }

    /**
     * 서브 카테고리 이름으로 서브 카테고리 조회
     *
     * @param name
     */
    @Override
    public ResponseSubCategoryDto getSubCategoryByName(String name) {
        SubCategory subCategory = subCategoryRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        return ResponseSubCategoryDto.from(subCategory);
    }

    /**
     * 서브 카테고리 전체 조회
     */
    @Override
    public List<ResponseSubCategoryDto> getAllSubCategories() {
        return subCategoryRepository.findAllByDeletedFalse().stream()
                .map(ResponseSubCategoryDto::from)
                .toList();
    }

    /**
     * 서브 카테고리 수정
     *
     * @param requestUpdateSubCategoryDto
     */
    @Transactional
    @Override
    public void updateSubCategory(RequestUpdateSubCategoryDto requestUpdateSubCategoryDto) {
        Category category = categoryRepository.findById(requestUpdateSubCategoryDto.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        SubCategory subCategory = subCategoryRepository.findById(requestUpdateSubCategoryDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        subCategoryRepository.save(requestUpdateSubCategoryDto.updateEntity(category));
    }

    /**
     * 서브 카테고리 삭제
     *
     * @param requestDeleteSubCategoryDto
     */
    @Transactional
    @Override
    public void deleteSubCategory(RequestDeleteSubCategoryDto requestDeleteSubCategoryDto) {
        SubCategory subCategory = subCategoryRepository.findById(requestDeleteSubCategoryDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        subCategory.softDelete();
    }
}
