package com.starbucks.back.option.application;

import com.starbucks.back.option.dto.in.RequestColorDto;
import com.starbucks.back.option.dto.out.ResponseColorDto;

import java.util.List;

public interface ColorService {

    /**
     * 색상 추가
     * @param requestColorDto
     */
    void addColor(RequestColorDto requestColorDto);

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
     * 색상 수정
     * @param requestColorDto
     */
    void updateColor(RequestColorDto requestColorDto);

    /**
     * 색상 삭제
     * @param requestColorDto
     */
    void deleteColor(RequestColorDto requestColorDto);

}
