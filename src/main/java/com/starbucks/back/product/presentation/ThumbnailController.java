package com.starbucks.back.product.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.product.application.ThumbnailService;
import com.starbucks.back.product.dto.in.RequestAddThumbnailDto;
import com.starbucks.back.product.dto.in.RequestDeleteThumbnailDto;
import com.starbucks.back.product.dto.in.RequestUpdateThumbnailDto;
import com.starbucks.back.product.dto.out.ResponseThumbnailDto;
import com.starbucks.back.product.vo.in.RequestDeleteThumbnailVo;
import com.starbucks.back.product.vo.in.RequestThumbnailVo;
import com.starbucks.back.product.vo.out.ResponseThumbnailVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/product/thumbnail")
@RestController
@RequiredArgsConstructor
public class ThumbnailController {

    private final ThumbnailService thumbnailService;

    /**
     * 썸네일 추가
     * @param requestAddThumbnailDto
     */
    @Operation(summary = "AddThumbnail API", description = "AddThumbnail API 입니다.", tags = {"Product-Thumbnail-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addThumbnail(@RequestBody RequestAddThumbnailDto requestAddThumbnailDto) {
        thumbnailService.addThumbnail(requestAddThumbnailDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * id로 썸네일 조회
     * @param id
     */
    @Operation(summary = "getThumbnailById API", description = "getThumbnailById API 입니다.", tags = {"Product-Thumbnail-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseThumbnailVo> getThumbnailById(@PathVariable(name = "id") Long id) {
        ResponseThumbnailDto responseThumbnailDto = thumbnailService.getThumbnailById(id);
        return new BaseResponseEntity<>(responseThumbnailDto.toVo());
    }

    /**
     * 상품 UUID로 썸네일 조회
     * @param productUuid
     */
    @Operation(summary = "GetThumbnailByProductUuid API", description = "GetThumbnailByProductUuid API 입니다.", tags = {"Product-Thumbnail-Service"})
    @GetMapping("/list/{productUuid}")
    public BaseResponseEntity<List<ResponseThumbnailVo>> getThumbnailByProductUuid(@PathVariable String productUuid) {
        List<ResponseThumbnailVo> result = thumbnailService.getThumbnailByProductUuid(productUuid)
                .stream()
                .map(ResponseThumbnailDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 썸네일 전체 조회
     */
    @Operation(summary = "GetAllThumbnails API", description = "GetAllThumbnails API 입니다.", tags = {"Product-Thumbnail-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseThumbnailVo>> getAllThumbnails() {
        List<ResponseThumbnailVo> result = thumbnailService.getAllThumbnails()
                .stream()
                .map(ResponseThumbnailDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 썸네일 수정
     * @param requestThumbnailVo
     */
    @Operation(summary = "UpdateThumbnail API", description = "UpdateThumbnail API 입니다.", tags = {"Product-Thumbnail-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateThumbnail(@RequestBody RequestThumbnailVo requestThumbnailVo) {
        thumbnailService.updateThumbnail(RequestUpdateThumbnailDto.of(requestThumbnailVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 썸네일 삭제
     * @param requestDeleteThumbnailVo
     */
    @Operation(summary = "DeleteThumbnail API", description = "DeleteThumbnail API 입니다.", tags = {"Product-Thumbnail-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteThumbnail(@RequestBody RequestDeleteThumbnailVo requestDeleteThumbnailVo) {
        thumbnailService.deleteThumbnail(RequestDeleteThumbnailDto.from(requestDeleteThumbnailVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
