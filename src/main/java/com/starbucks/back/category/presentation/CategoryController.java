package com.starbucks.back.category.presentation;

import com.starbucks.back.category.application.CategoryService;
import com.starbucks.back.category.dto.in.RequestAddCategoryDto;
import com.starbucks.back.category.dto.in.RequestDeleteCategoryDto;
import com.starbucks.back.category.dto.in.RequestUpdateCategoryDto;
import com.starbucks.back.category.dto.out.ResponseCategoryDto;
import com.starbucks.back.category.vo.in.RequestAddCategoryVo;
import com.starbucks.back.category.vo.in.RequestCategoryVo;
import com.starbucks.back.category.vo.in.RequestDeleteCategoryVo;
import com.starbucks.back.category.vo.out.ResponseCategoryVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
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
     * @param requestAddCategoryVo
     */
    @Operation(summary = "카테고리 추가 API", description = "카테고리 추가 API 입니다.", tags = {"Category-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addCategory(@RequestBody RequestAddCategoryVo requestAddCategoryVo) {
        categoryService.addCategory(RequestAddCategoryDto.from(requestAddCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 카테고리 id로 카테고리 조회
     * @param id
     */
    @Operation(summary = "id로 카테고리 조회 API", description = "id로 카테고리 조회 API 입니다.", tags = {"Category-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseCategoryVo> getCategoryById(@PathVariable("id") Long id) {
        ResponseCategoryDto responseCategoryDto = categoryService.getCategoryById(id);
        return new BaseResponseEntity<>(responseCategoryDto.toVo());
    }

    /**
     * 카테고리 이름으로 카테고리 조회
     * @param name
     */
    @Operation(summary = "이름으로 카테고리 조회 API", description = "이름으로 카테고리 조회 API 입니다.", tags = {"Category-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseCategoryVo> getCategoryByName(@RequestParam String name) {
        ResponseCategoryDto responseCategoryDto = categoryService.getCategoryByName(name);
        return new BaseResponseEntity<>(responseCategoryDto.toVo());
    }

    /**
     * 카테고리 전체 조회
     */
    @Operation(summary = "카테고리 전체 조회 API", description = "카테고리 전체 조회 API 입니다.", tags = {"Category-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseCategoryVo>> getAllCategories() {
        List<ResponseCategoryVo> result = categoryService.getAllCategories().stream()
                .map(ResponseCategoryDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 카테고리 수정
     * @param requestCategoryVo
     */
    @Operation(summary = "카테고리 수정 API", description = "카테고리 수정 API 입니다.", tags = {"Category-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateCategory(@RequestBody RequestCategoryVo requestCategoryVo) {
        categoryService.updateCategory(RequestUpdateCategoryDto.from(requestCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 카테고리 삭제
     * @param requestDeleteCategoryVo
     */
    @Operation(summary = "카테고리 삭제 API", description = "카테고리 삭제 API 입니다.", tags = {"Category-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteCategory(@RequestBody RequestDeleteCategoryVo requestDeleteCategoryVo) {
        categoryService.deleteCategory(RequestDeleteCategoryDto.of(requestDeleteCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
