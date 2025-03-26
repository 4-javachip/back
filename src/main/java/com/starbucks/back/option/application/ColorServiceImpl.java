package com.starbucks.back.option.application;

import com.starbucks.back.option.domain.Color;
import com.starbucks.back.option.dto.in.RequestColorDto;
import com.starbucks.back.option.dto.out.ResponseColorDto;
import com.starbucks.back.option.infrastructure.ColorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Transactional
    @Override
    public void addColor(RequestColorDto requestColorDto) {
        if (colorRepository.existsByNameAndDeletedFalse(requestColorDto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 색상입니다.");
        }
        colorRepository.save(requestColorDto.toEntity());
    }

    
    @Override
    public ResponseColorDto getColorById(Long id) {
        Color color = colorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 색상입니다."));
        return ResponseColorDto.from(color);
    }

    @Override
    public ResponseColorDto getColorByName(String name) {
        Color color = colorRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 색상입니다."));
        return ResponseColorDto.from(color);
    }

    @Transactional
    @Override
    public void updateColor(RequestColorDto requestColorDto) {
        Optional<Color> existing = colorRepository.findByNameAndDeletedFalse(requestColorDto.getName());

        if (existing.isPresent() && !existing.get().getId().equals(requestColorDto.getId())) {
            throw new IllegalArgumentException("이미 존재하는 색상 이름입니다.");
        }
        colorRepository.save(requestColorDto.updateEntity());
    }

    @Transactional
    @Override
    public void deleteColor(RequestColorDto requestColorDto) {
        Color color = colorRepository.findById(requestColorDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("색상을 찾을 수 없습니다."));
        color.softDelete();
    }
}
