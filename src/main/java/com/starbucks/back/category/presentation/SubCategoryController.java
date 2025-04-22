package com.starbucks.back.category.presentation;

import com.starbucks.back.category.application.SubCategoryService;
import com.starbucks.back.category.dto.in.RequestAddSubCategoryDto;
import com.starbucks.back.category.dto.in.RequestDeleteSubCategoryDto;
import com.starbucks.back.category.dto.in.RequestUpdateSubCategoryDto;
import com.starbucks.back.category.dto.out.ResponseSubCategoryDto;
import com.starbucks.back.category.vo.in.RequestAddSubCategoryVo;
import com.starbucks.back.category.vo.in.RequestDeleteCategoryVo;
import com.starbucks.back.category.vo.in.RequestSubCategoryVo;
import com.starbucks.back.category.vo.out.ResponseSubCategoryVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/sub-category")
@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    /**
     * 서브 카테고리 추가
     * @param requestAddSubCategoryVo
     */
    @Operation(summary = "서브 카테고리 추가 API", description = "서브 카테고리 추가 API 입니다.", tags = {"Sub-Category-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addSubCategory(@RequestBody RequestAddSubCategoryVo requestAddSubCategoryVo) {
        subCategoryService.addSubCategory(RequestAddSubCategoryDto.from(requestAddSubCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 서브 카테고리 id로 서브 카테고리 조회
     * @param id
     */
    @Operation(summary = "id로 서브 카테고리 조회 API", description = "id로 서브 카테고리 조회 API 입니다.", tags = {"Sub-Category-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseSubCategoryVo> getSubCategoryById(@PathVariable("id") Long id) {
        ResponseSubCategoryDto responseSubCategoryDto = subCategoryService.getSubCategoryById(id);
        return new BaseResponseEntity<>(responseSubCategoryDto.toVo());
    }

    /**
     * 서브 카테고리 이름으로 서브 카테고리 조회
     * @param name
     */
    @Operation(summary = "이름으로 서브 카테고리 조회 API", description = "이름으로 서브 카테고리 조회 API 입니다.", tags = {"Sub-Category-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseSubCategoryVo> getSubCategoryByName(@RequestParam("name") String name) {
        ResponseSubCategoryDto responseSubCategoryDto = subCategoryService.getSubCategoryByName(name);
        return new BaseResponseEntity<>(responseSubCategoryDto.toVo());
    }

    /**
     * 카테고리 id로 서브 카테고리 리스트 조회
     * @param categoryId
     */
    @Operation(summary = "카테고리 id로 서브 카테고리 리스트 조회 API", description = "카테고리 id로 서브 카테고리 리스트 조회 API 입니다.", tags = {"Sub-Category-Service"})
    @GetMapping("/list/category/{categoryId}")
    public BaseResponseEntity<List<ResponseSubCategoryVo>> getSubCategoryByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<ResponseSubCategoryVo> result = subCategoryService.getSubCategoryByCategoryId(categoryId)
                .stream()
                .map(ResponseSubCategoryDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 서브 카테고리 전체 조회
     */
    @Operation(summary = "서브 카테고리 전체 조회 API", description = "서브 카테고리 전체 조회 API 입니다.", tags = {"Sub-Category-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseSubCategoryVo>> getAllSubCategories() {
        List<ResponseSubCategoryVo> result = subCategoryService.getAllSubCategories()
                .stream()
                .map(ResponseSubCategoryDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 서브 카테고리 수정
     * @param requestSubCategoryVo
     */
    @Operation(summary = "서브 카테고리 수정 API", description = "서브 카테고리 수정 API 입니다.", tags = {"Sub-Category-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateSubCategory(@RequestBody RequestSubCategoryVo requestSubCategoryVo) {
        subCategoryService.updateSubCategory(RequestUpdateSubCategoryDto.from(requestSubCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 서브 카테고리 삭제
     * @param requestDeleteCategoryVo
     */
    @Operation(summary = "서브 카테고리 삭제 API", description = "서브 카테고리 삭제 API 입니다.", tags = {"Sub-Category-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteSubCategory(@RequestBody RequestDeleteCategoryVo requestDeleteCategoryVo) {
        subCategoryService.deleteSubCategory(RequestDeleteSubCategoryDto.of(requestDeleteCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
