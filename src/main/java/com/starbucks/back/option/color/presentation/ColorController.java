package com.starbucks.back.option.color.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.option.color.application.ColorService;
import com.starbucks.back.option.color.dto.in.RequestColorDto;
import com.starbucks.back.option.color.dto.out.ResponseColorDto;
import com.starbucks.back.option.color.vo.in.RequestColorVo;
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
     * @param requestColorDto
     */
    @Operation(summary = "CreateColor API", description = "CreateColor API 입니다.", tags = {"Color-service"})
    @PostMapping
    public BaseResponseEntity<Void> createColor(@RequestBody RequestColorDto requestColorDto) {
        colorService.addColor(requestColorDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 색상 id로 색상 조회
     * @param id
     */
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseColorVo> getColorById(@PathVariable("id") Long id) {
        ResponseColorDto responseColorDto = colorService.getColorById(id);
        return new BaseResponseEntity<>(responseColorDto.toVo());
    }

    /**
     * 색상 이름으로 색상 조회
     * @param name
     */
    @GetMapping("/search")
    public BaseResponseEntity<ResponseColorVo> getColorByName(@RequestParam String name) {
        ResponseColorDto responseColorDto = colorService.getColorByName(name);
        return new BaseResponseEntity<>(responseColorDto.toVo());
    }

    /**
     * 색상 전체 조회
     */
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
    @PutMapping
    public BaseResponseEntity<Void> updateColor(@RequestBody RequestColorVo requestColorVo) {
        colorService.updateColor(RequestColorDto.from(requestColorVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 색상 삭제
     * @param requestColorVo
     */
    @DeleteMapping
    public BaseResponseEntity<Void> deleteColor(@RequestBody RequestColorVo requestColorVo) {
        colorService.deleteColor(RequestColorDto.from(requestColorVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
