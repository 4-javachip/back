package com.starbucks.back.option.application;

import com.starbucks.back.option.domain.Color;
import com.starbucks.back.option.dto.in.RequestColorDto;
import com.starbucks.back.option.dto.out.ResponseColorDto;
import com.starbucks.back.option.infrastructure.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public void createColor(RequestColorDto requestColorDto) {
        if (colorRepository.existsByName(requestColorDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 색상입니다.");
        }
        colorRepository.save(requestColorDto.toEntity());
    }

    @Override
    public ResponseColorDto findColorById(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 색상입니다."));
        return ResponseColorDto.from(color);
    }

    @Override
    public ResponseColorDto findColorByName(String name) {
        Color color = colorRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 색상입니다."));
        return ResponseColorDto.from(color);
    }

    @Override
    public void updateColor(RequestColorDto requestColorDto) {
        colorRepository.findByName(requestColorDto.getName())
                .filter(color -> !color.getId().equals(requestColorDto.getId()))
                .ifPresent(c -> {
                    throw new IllegalArgumentException("이미 존재하는 색상 이름입니다.");
                });
        colorRepository.save(requestColorDto.updateEntity());
    }

    @Override
    public void deleteColor(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new EntityNotFoundException("존재하지 않는 색상입니다.");
        }
        colorRepository.deleteById(id);
    }
}
