package com.starbucks.back.option.size.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.option.size.application.SizeService;
import com.starbucks.back.option.size.dto.in.RequestSizeDto;
import com.starbucks.back.option.size.dto.out.ResponseSizeDto;
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
     * @param requestSizeDto
     */
    @Operation(summary = "AddSize API", description = "AddSize API 입니다.", tags = {"Size-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addSize(@RequestBody RequestSizeDto requestSizeDto) {
        sizeService.addSize(requestSizeDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 사이즈 id로 사이즈 조회
     * @param id
     */
    @Operation(summary = "GetSizeById API", description = "GetSizeById API 입니다.", tags = {"Size-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseSizeVo> getSizeById(@PathVariable("id") Long id) {
        ResponseSizeDto responseSizeDto = sizeService.getSizeById(id);
        return new BaseResponseEntity<>(responseSizeDto.toVo());
    }

    /**
     * 사이즈 이름으로 사이즈 조회
     * @param name
     */
    @Operation(summary = "GetSizeByName API", description = "GetSizeByName API 입니다.", tags = {"Size-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseSizeVo> getSizeByName(@RequestParam String name) {
        ResponseSizeDto responseSizeDto = sizeService.getSizeByName(name);
        return new BaseResponseEntity<>(responseSizeDto.toVo());
    }

    /**
     * 사이즈 전체 조회
     */
    @Operation(summary = "GetAllSizes API", description = "GetAllSizes API 입니다.", tags = {"Size-Service"})
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
    @Operation(summary = "UpdateSize API", description = "UpdateSize API 입니다.", tags = {"Size-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateSize(@RequestBody RequestSizeVo requestSizeVo) {
        sizeService.updateSize(RequestSizeDto.from(requestSizeVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 사이즈 삭제
     * @param requestSizeVo
     */
    @Operation(summary = "DeleteSize API", description = "DeleteSize API 입니다.", tags = {"Size-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteSize(@RequestBody RequestSizeVo requestSizeVo) {
        sizeService.deleteSize(RequestSizeDto.from(requestSizeVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
