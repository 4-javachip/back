package com.starbucks.back.option.color.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.option.color.domain.Color;
import com.starbucks.back.option.color.dto.in.RequestAddColorDto;
import com.starbucks.back.option.color.dto.in.RequestDeleteColorDto;
import com.starbucks.back.option.color.dto.in.RequestUpdateColorDto;
import com.starbucks.back.option.color.dto.out.ResponseColorDto;
import com.starbucks.back.option.color.infrastructure.ColorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    /**
     * 색상 추가
     * @param requestAddColorDto
     */
    @Transactional
    @Override
    public void addColor(RequestAddColorDto requestAddColorDto) {
        if (colorRepository.existsByName(requestAddColorDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_OPTION);
        }
        colorRepository.save(requestAddColorDto.toEntity());
    }

    /**
     * id로 색상 조회
     * @param id
     */
    @Override
    public ResponseColorDto getColorById(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        return ResponseColorDto.from(color);
    }

    /**
     * 색상 이름으로 색상 조회
     * @param name
     */
    @Override
    public ResponseColorDto getColorByName(String name) {
        Color color = colorRepository.findByName(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        return ResponseColorDto.from(color);
    }

    /**
     * 색상 전체 조회
     */
    @Override
    public List<ResponseColorDto> getAllColors() {
        return colorRepository.findAll().stream()
                .map(ResponseColorDto::from)
                .toList();
    }

    /**
     * 색상 수정
     * @param requestUpdateColorDto
     */
    @Transactional
    @Override
    public void updateColor(RequestUpdateColorDto requestUpdateColorDto) {
        colorRepository.save(requestUpdateColorDto.updateEntity());
    }

    /**
     * 색상 삭제
     * @param requestDeleteColorDto
     */
    @Transactional
    @Override
    public void deleteColor(RequestDeleteColorDto requestDeleteColorDto) {
        Color color = colorRepository.findById(requestDeleteColorDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        colorRepository.delete(color);
    }
}
