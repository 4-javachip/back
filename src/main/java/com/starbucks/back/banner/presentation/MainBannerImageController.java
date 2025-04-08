package com.starbucks.back.banner.presentation;

import com.starbucks.back.banner.application.MainBannerImageService;
import com.starbucks.back.banner.dto.in.RequestAddMainBannerImageDto;
import com.starbucks.back.banner.dto.in.RequestDeleteMainBannerImageDto;
import com.starbucks.back.banner.dto.in.RequestUpdateMainBannerImageDto;
import com.starbucks.back.banner.dto.out.ResponseMainBannerImageDto;
import com.starbucks.back.banner.vo.in.RequestAddMainBannerImageVo;
import com.starbucks.back.banner.vo.in.RequestDeleteMainBannerImageVo;
import com.starbucks.back.banner.vo.in.RequestMainBannerImageVo;
import com.starbucks.back.banner.vo.out.ResponseMainBannerImageVo;
import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/banner")
@RestController
@RequiredArgsConstructor
public class MainBannerImageController {

    private final MainBannerImageService mainBannerImageService;

    /**
     * 메인 배너 이미지 생성
     * @param requestAddMainBannerImageVo
     */
    @Operation(summary = "메인 배너 이미지 생성 API", description = "메인 배너 이미지 생성 API 입니다.", tags = {"Main-Banner-Image-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addMainBannerImage(@RequestBody RequestAddMainBannerImageVo requestAddMainBannerImageVo) {
        mainBannerImageService.addMainBannerImage(RequestAddMainBannerImageDto.from(requestAddMainBannerImageVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "메인 배너 이미지 uuid로 조회 API", description = "메인 배너 이미지 uuid로 조회 API 입니다.", tags = {"Main-Banner-Image-Service"})
    @GetMapping("{mainBannerImageUuid}")
    public BaseResponseEntity<ResponseMainBannerImageVo> getMainBannerImageByMainBannerImageUuid(@PathVariable String mainBannerImageUuid) {
        ResponseMainBannerImageDto responseMainBannerImageDto = mainBannerImageService.getMainBannerImageByMainBannerImageUuid(mainBannerImageUuid);
        return new BaseResponseEntity<>(responseMainBannerImageDto.toVo());
    }

    /**
     * 메인 배너 이미지 전체 조회
     */
    @Operation(summary = "메인 배너 이미지 전체 조회 API", description = "메인 배너 이미지 전체 조회 API 입니다.", tags = {"Main-Banner-Image-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseMainBannerImageVo>> getAllMainBannerImage() {
        List<ResponseMainBannerImageVo> result = mainBannerImageService.getAllMainBannerImage()
                .stream()
                .map(ResponseMainBannerImageDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 메인 배너 이미지 수정
     * @param requestMainBannerImageVo
     */
    @Operation(summary = "메인 배너 이미지 수정 API", description = "메인 배너 이미지 수정 API 입니다.", tags = {"Main-Banner-Image-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateMainBannerImage(@RequestBody RequestMainBannerImageVo requestMainBannerImageVo) {
        mainBannerImageService.updateMainBannerImage(RequestUpdateMainBannerImageDto.from(requestMainBannerImageVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 메인 배너 이미지 삭제
     * @param requestDeleteMainBannerImageVo
     */
    @Operation(summary = "메인 배너 이미지 삭제 API", description = "메인 배너 이미지 삭제 API 입니다.", tags = {"Main-Banner-Image-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteMainBannerImage(@RequestBody RequestDeleteMainBannerImageVo requestDeleteMainBannerImageVo) {
        mainBannerImageService.deleteMainBannerImage(RequestDeleteMainBannerImageDto.of(requestDeleteMainBannerImageVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
