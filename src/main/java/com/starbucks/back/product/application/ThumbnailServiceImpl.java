package com.starbucks.back.product.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.product.domain.Thumbnail;
import com.starbucks.back.product.dto.in.RequestAddThumbnailDto;
import com.starbucks.back.product.dto.in.RequestDeleteThumbnailDto;
import com.starbucks.back.product.dto.in.RequestUpdateThumbnailDto;
import com.starbucks.back.product.dto.out.ResponseThumbnailDto;
import com.starbucks.back.product.infrastructure.ThumbnailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThumbnailServiceImpl implements ThumbnailService {

    private final ThumbnailRepository thumbnailRepository;

    /**
     * 썸네일 추가
     * @param requestAddThumbnailDto
     */
    @Transactional
    @Override
    public void addThumbnail(RequestAddThumbnailDto requestAddThumbnailDto) {
        thumbnailRepository.save(requestAddThumbnailDto.toEntity());
    }

    /**
     * 썸네일 id로 조회
     * @param id
     */
    @Override
    public ResponseThumbnailDto getThumbnailById(Long id) {
        Thumbnail thumbnail = thumbnailRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return ResponseThumbnailDto.from(thumbnail);
    }

    /**
     * 상품 UUID로 썸네일 조회
     * @param productUuid
     */
    @Override
    public List<ResponseThumbnailDto> getThumbnailByProductUuid(String productUuid) {
        return thumbnailRepository.findAllByProductUuidAndDeletedFalseOrderByDefaultedDescIdAsc(productUuid)
                .stream()
                .map(ResponseThumbnailDto::from)
                .toList();
    }

    /**
     * 삭제되지 않은 메인 이미지 전체 조회
     */
    @Override
    public List<ResponseThumbnailDto> getThumbnailByDefaultedTrue() {
        return thumbnailRepository.findAllByDeletedFalseAndDefaultedTrue()
                .stream()
                .map(ResponseThumbnailDto::from)
                .toList();
    }

    /**
     * 썸네일 전체 조회
     */
    @Override
    public List<ResponseThumbnailDto> getAllThumbnails() {
        return thumbnailRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseThumbnailDto::from)
                .toList();
    }

    /**
     * 썸네일 수정
     * @param requestUpdateThumbnailDto
     */
    @Transactional
    @Override
    public void updateThumbnail(RequestUpdateThumbnailDto requestUpdateThumbnailDto) {
        Thumbnail thumbnail = thumbnailRepository.findByIdAndDeletedFalse(requestUpdateThumbnailDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        thumbnailRepository.save(requestUpdateThumbnailDto.updateEntity(thumbnail));
    }

    /**
     * 썸네일 삭제
     * @param requestDeleteThumbnailDto
     */
    @Transactional
    @Override
    public void deleteThumbnail(RequestDeleteThumbnailDto requestDeleteThumbnailDto) {
        Thumbnail thumbnail = thumbnailRepository.findByIdAndDeletedFalse(requestDeleteThumbnailDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        thumbnail.softDelete();
    }
}
