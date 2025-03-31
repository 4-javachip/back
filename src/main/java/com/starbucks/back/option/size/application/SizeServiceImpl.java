package com.starbucks.back.option.size.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.option.size.domain.Size;
import com.starbucks.back.option.size.dto.in.RequestAddSizeDto;
import com.starbucks.back.option.size.dto.in.RequestUpdateSizeDto;
import com.starbucks.back.option.size.dto.out.ResponseSizeDto;
import com.starbucks.back.option.size.infrastructure.SizeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    /**
     * 사이즈 추가
     * @param requestAddSizeDto
     */
    @Transactional
    @Override
    public void addSize(RequestAddSizeDto requestAddSizeDto) {
        if(sizeRepository.existsByNameAndDeletedFalse(requestAddSizeDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_OPTION);
        }
        sizeRepository.save(requestAddSizeDto.toEntity());
    }

    /**
     * id로 사이즈 조회
     * @param id
     */
    @Override
    public ResponseSizeDto getSizeById(Long id) {
        Size size = sizeRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        return ResponseSizeDto.from(size);
    }

    /**
     * 사이즈 이름으로 사이즈 조회
     * @param name
     */
    @Override
    public ResponseSizeDto getSizeByName(String name) {
        Size size = sizeRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        return ResponseSizeDto.from(size);
    }

    /**
     * 사이즈 전체 조회
     */
    @Override
    public List<ResponseSizeDto> getAllSizes() {
        return sizeRepository.findAllByDeletedFalse().stream()
                .map(ResponseSizeDto::from)
                .toList();
    }

    /**
     * 사이즈 수정
     * @param requestUpdateSizeDto
     */
    @Transactional
    @Override
    public void updateSize(RequestUpdateSizeDto requestUpdateSizeDto) {
        sizeRepository.save(requestUpdateSizeDto.updateEntity());
    }

    /**
     * 사이즈 삭제
     * @param requestUpdateSizeDto
     */
    @Transactional
    @Override
    public void deleteSize(RequestUpdateSizeDto requestUpdateSizeDto) {
        Size size = sizeRepository.findById(requestUpdateSizeDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_OPTION));
        size.softDelete();
    }
}