package com.starbucks.back.option.size.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.option.size.application.SizeService;
import com.starbucks.back.option.size.dto.in.RequestAddSizeDto;
import com.starbucks.back.option.size.dto.in.RequestDeleteSizeDto;
import com.starbucks.back.option.size.dto.in.RequestUpdateSizeDto;
import com.starbucks.back.option.size.dto.out.ResponseSizeDto;
import com.starbucks.back.option.size.vo.in.RequestAddSizeVo;
import com.starbucks.back.option.size.vo.in.RequestDeleteSizeVo;
import com.starbucks.back.option.size.vo.in.RequestSizeVo;
import com.starbucks.back.option.size.vo.out.ResponseSizeVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/option/size")
@RestController
@RequiredArgsConstructor
public class SizeController {

    private final SizeService sizeService;

    /**
     * 사이즈 추가
     * @param requestAddSizeVo
     */
    @Operation(summary = "사이즈 옵션 추가 API", description = "사이즈 옵션 추가 API 입니다.", tags = {"Size-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addSize(@RequestBody RequestAddSizeVo requestAddSizeVo) {
        sizeService.addSize(RequestAddSizeDto.from(requestAddSizeVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 사이즈 id로 사이즈 조회
     * @param id
     */
    @Operation(summary = "id로 사이즈 옵션 조회 API", description = "id로 사이즈 옵션 조회 API 입니다.", tags = {"Size-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseSizeVo> getSizeById(@PathVariable("id") Long id) {
        ResponseSizeDto responseSizeDto = sizeService.getSizeById(id);
        return new BaseResponseEntity<>(responseSizeDto.toVo());
    }

    /**
     * 사이즈 이름으로 사이즈 조회
     * @param name
     */
    @Operation(summary = "이름으로 사이즈 옵션 조회 API", description = "이름으로 사이즈 옵션 조회 API 입니다.", tags = {"Size-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseSizeVo> getSizeByName(@RequestParam("name") String name) {
        ResponseSizeDto responseSizeDto = sizeService.getSizeByName(name);
        return new BaseResponseEntity<>(responseSizeDto.toVo());
    }

    /**
     * 사이즈 전체 조회
     */
    @Operation(summary = "사이즈 옵션 전체 조회 API", description = "사이즈 옵션 전체 조회 API 입니다.", tags = {"Size-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseSizeVo>> getAllSizes() {
        List<ResponseSizeVo> result = sizeService.getAllSizes().stream()
                .map(ResponseSizeDto::toVo)
                .collect(Collectors.toList());
        return new BaseResponseEntity<>(result);
    }

    /**
     * 사이즈 수정
     * @param requestSizeVo
     */
    @Operation(summary = "사이즈 옵션 수정 API", description = "사이즈 옵션 수정 API 입니다.", tags = {"Size-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateSize(@RequestBody RequestSizeVo requestSizeVo) {
        sizeService.updateSize(RequestUpdateSizeDto.from(requestSizeVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 사이즈 삭제
     * @param requestDeleteSizeVo
     */
    @Operation(summary = "사이즈 옵션 삭제 API", description = "사이즈 옵션 삭제 API 입니다.", tags = {"Size-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteSize(@RequestBody RequestDeleteSizeVo requestDeleteSizeVo) {
        sizeService.deleteSize(RequestDeleteSizeDto.of(requestDeleteSizeVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
