package com.starbucks.back.option.presentation;

import com.starbucks.back.option.application.ColorService;
import com.starbucks.back.option.dto.in.RequestColorDto;
import com.starbucks.back.option.dto.out.ResponseColorDto;
import com.starbucks.back.option.vo.in.RequestColorVo;
import com.starbucks.back.option.vo.out.ResponseColorVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/option/colors")
@RestController
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @PostMapping
    public void createColor(@RequestBody RequestColorDto requestColorDto) {
        colorService.createColor(requestColorDto);
    }

    @GetMapping("/{id}")
    public ResponseColorVo findColorById(@PathVariable Long id) {
        ResponseColorDto responseColorDto = colorService.findColorById(id);
        return responseColorDto.toVo();
    }

    @PutMapping
    public void updateColor(@RequestBody RequestColorVo requestColorVo) {
        colorService.updateColor(RequestColorDto.from(requestColorVo));
    }

    @DeleteMapping("/{id}")
    public void deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
    }

}
