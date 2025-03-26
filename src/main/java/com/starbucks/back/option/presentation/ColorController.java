package com.starbucks.back.option.presentation;

import com.starbucks.back.option.application.ColorService;
import com.starbucks.back.option.dto.in.RequestColorDto;
import com.starbucks.back.option.dto.out.ResponseColorDto;
import com.starbucks.back.option.vo.in.RequestColorVo;
import com.starbucks.back.option.vo.out.ResponseColorVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/option/color")
@RestController
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping
    public void createColor(@RequestBody RequestColorDto requestColorDto) {
        colorService.addColor(requestColorDto);
    }

    @GetMapping("/{id}")
    public ResponseColorVo getColorById(@PathVariable("id") Long id) {
        ResponseColorDto responseColorDto = colorService.getColorById(id);
        return responseColorDto.toVo();
    }

    @GetMapping("/search")
    public ResponseColorVo getColorByName(@RequestParam String name) {
        ResponseColorDto responseColorDto = colorService.getColorByName(name);
        return responseColorDto.toVo();
    }

    @PutMapping
    public void updateColor(@RequestBody RequestColorVo requestColorVo) {
        colorService.updateColor(RequestColorDto.from(requestColorVo));
    }

    @DeleteMapping
    public void deleteColor(@RequestBody RequestColorVo requestColorVo) {
        colorService.deleteColor(RequestColorDto.from(requestColorVo));
    }

}
