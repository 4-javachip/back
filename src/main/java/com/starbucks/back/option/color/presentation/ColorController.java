package com.starbucks.back.option.color.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.option.color.application.ColorService;
import com.starbucks.back.option.color.dto.in.RequestAddColorDto;
//import com.starbucks.back.option.color.dto.in.RequestColorDto;
import com.starbucks.back.option.color.dto.in.RequestDeleteColorDto;
import com.starbucks.back.option.color.dto.in.RequestUpdateColorDto;
import com.starbucks.back.option.color.dto.out.ResponseColorDto;
import com.starbucks.back.option.color.vo.in.RequestAddColorVo;
import com.starbucks.back.option.color.vo.in.RequestColorVo;
import com.starbucks.back.option.color.vo.in.RequestDeleteColorVo;
import com.starbucks.back.option.color.vo.out.ResponseColorVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/option/color")
@RestController
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    /**
     * 색상 추가
     * @param requestAddColorVo
     */
    @Operation(summary = "색상 옵션 추가 API", description = "색상 옵션 추가 API 입니다.", tags = {"Color-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addColor(@RequestBody RequestAddColorVo requestAddColorVo) {
        colorService.addColor(RequestAddColorDto.from(requestAddColorVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 색상 id로 색상 조회
     * @param id
     */
    @Operation(summary = "id로 색상 옵션 조회 API", description = "id로 색상 옵션 조회 API 입니다.", tags = {"Color-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseColorVo> getColorById(@PathVariable("id") Long id) {
        ResponseColorDto responseColorDto = colorService.getColorById(id);
        return new BaseResponseEntity<>(responseColorDto.toVo());
    }

    /**
     * 색상 이름으로 색상 조회
     * @param name
     */
    @Operation(summary = "이름으로 색상 옵션 조회 API", description = "이름으로 색상 옵션 조회 API 입니다.", tags = {"Color-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseColorVo> getColorByName(@RequestParam("name") String name) {
        ResponseColorDto responseColorDto = colorService.getColorByName(name);
        return new BaseResponseEntity<>(responseColorDto.toVo());
    }

    /**
     * 색상 전체 조회
     */
    @Operation(summary = "색상 옵션 전체 조회 API", description = "색상 옵션 전체 조회 API 입니다.", tags = {"Color-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseColorVo>> getAllColors() {
        List<ResponseColorVo> result = colorService.getAllColors().stream()
                .map(ResponseColorDto::toVo)
                .collect(Collectors.toList());
        return new BaseResponseEntity<>(result);
    }

    /**
     * 색상 수정
     * @param requestColorVo
     */
    @Operation(summary = "색상 옵션 수정 API", description = "색상 옵션 수정 API 입니다.", tags = {"Color-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateColor(@RequestBody RequestColorVo requestColorVo) {
        colorService.updateColor(RequestUpdateColorDto.from(requestColorVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 색상 삭제
     * @param requestDeleteColorVo
     */
    @Operation(summary = "색상 옵션 삭제 API", description = "색상 옵션 삭제 API 입니다.", tags = {"Color-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteColor(@RequestBody RequestDeleteColorVo requestDeleteColorVo) {
        colorService.deleteColor(RequestDeleteColorDto.of(requestDeleteColorVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
