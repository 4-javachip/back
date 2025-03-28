package com.starbucks.back.category.presentation;

import com.starbucks.back.category.application.CategoryService;
import com.starbucks.back.category.dto.in.RequestCategoryDto;
import com.starbucks.back.category.dto.out.ResponseCategoryDto;
import com.starbucks.back.category.vo.in.RequestCategoryVo;
import com.starbucks.back.category.vo.out.ResponseCategoryVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 카테고리 추가
     * @param requestCategoryDto
     */
    @Operation(summary = "AddCategory API", description = "AddCategory API 입니다.", tags = {"Category-Service"})
    @Transactional
    @PostMapping
    public BaseResponseEntity<Void> addCategory(@RequestBody RequestCategoryDto requestCategoryDto) {
        categoryService.addCategory(requestCategoryDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 카테고리 id로 카테고리 조회
     * @param id
     */
    @Operation(summary = "GetCategoryById API", description = "GetCategoryById API 입니다.", tags = {"Category-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseCategoryVo> getCategoryById(@PathVariable("id") Long id) {
        ResponseCategoryDto responseCategoryDto = categoryService.getCategoryById(id);
        return new BaseResponseEntity<>(responseCategoryDto.toVo());
    }

    /**
     * 카테고리 이름으로 카테고리 조회
     * @param name
     */
    @Operation(summary = "GetCategoryByName API", description = "GetCategoryByName API 입니다.", tags = {"Category-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseCategoryVo> getCategoryByName(@RequestParam String name) {
        ResponseCategoryDto responseCategoryDto = categoryService.getCategoryByName(name);
        return new BaseResponseEntity<>(responseCategoryDto.toVo());
    }

    /**
     * 카테고리 전체 조회
     */
    @Operation(summary = "GetAllCategories API", description = "GetAllCategories API 입니다.", tags = {"Category-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseCategoryVo>> getAllCategories() {
        List<ResponseCategoryVo> result = categoryService.getAllCategories().stream()
                .map(ResponseCategoryDto::toVo)
                .collect(Collectors.toList());
        return new BaseResponseEntity<>(result);
    }

    /**
     * 카테고리 수정
     * @param requestCategoryVo
     */
    @Operation(summary = "UpdateCategory API", description = "UpdateCategory API 입니다.", tags = {"Category-Service"})
    @Transactional
    @PutMapping
    public BaseResponseEntity<Void> updateCategory(@RequestBody RequestCategoryVo requestCategoryVo) {
        categoryService.updateCategory(RequestCategoryDto.from(requestCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 카테고리 삭제
     * @param requestCategoryVo
     */
    @Operation(summary = "DeleteCategory API", description = "DeleteCategory API 입니다.", tags = {"Category-Service"})
    @Transactional
    @DeleteMapping
    public BaseResponseEntity<Void> deleteCategory(@RequestBody RequestCategoryVo requestCategoryVo) {
        categoryService.deleteCategory(RequestCategoryDto.from(requestCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
