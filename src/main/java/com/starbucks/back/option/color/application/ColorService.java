package com.starbucks.back.option.color.application;

import com.starbucks.back.option.color.dto.in.RequestAddColorDto;
//import com.starbucks.back.option.color.dto.in.RequestColorDto;
import com.starbucks.back.option.color.dto.in.RequestDeleteColorDto;
import com.starbucks.back.option.color.dto.in.RequestUpdateColorDto;
import com.starbucks.back.option.color.dto.out.ResponseColorDto;

import java.util.List;

public interface ColorService {

    /**
     * 색상 추가
     * @param requestAddColorDto
     */
    void addColor(RequestAddColorDto requestAddColorDto);

    /**
     * id로 색상 조회
     * @param id
     */
    ResponseColorDto getColorById(Long id);

    /**
     * 색상 이름으로 색상 조회
     * @param name
     */
    ResponseColorDto getColorByName(String name);

    /**
     * 색상 전체 조회
     */
    List<ResponseColorDto> getAllColors();

    /**
     * 색상 수정
     * @param requestUpdateColorDto
     */
    void updateColor(RequestUpdateColorDto requestUpdateColorDto);

    /**
     * 색상 삭제
     * @param requestDeleteColorDto
     */
    void deleteColor(RequestDeleteColorDto requestDeleteColorDto);

}
