package com.starbucks.back.banner.application;

import com.starbucks.back.banner.domain.MainBannerImage;
import com.starbucks.back.banner.dto.in.RequestAddMainBannerImageDto;
import com.starbucks.back.banner.dto.in.RequestDeleteMainBannerImageDto;
import com.starbucks.back.banner.dto.in.RequestUpdateMainBannerImageDto;
import com.starbucks.back.banner.dto.out.ResponseMainBannerImageDto;
import com.starbucks.back.banner.infrastructure.MainBannerImageRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainBannerImageServiceImpl implements MainBannerImageService {

    private final MainBannerImageRepository mainBannerImageRepository;


    /**
     * 메인 배너 이미지 추가
     * @param requestAddMainBannerImageDto
     */
    @Transactional
    @Override
    public void addMainBannerImage(RequestAddMainBannerImageDto requestAddMainBannerImageDto) {
        mainBannerImageRepository.save(requestAddMainBannerImageDto.toEntity());
    }

    /**
     * 메인 배너 이미지 uuid로 메인 배너 이미지 조회
     * @param mainBannerImageUuid
     */
    @Override
    public ResponseMainBannerImageDto getMainBannerImageByMainBannerImageUuid(String mainBannerImageUuid) {
        MainBannerImage mainBannerImage = mainBannerImageRepository.findByMainBannerImageUuid(mainBannerImageUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_MAIN_BANNER));
        return ResponseMainBannerImageDto.from(mainBannerImage);
    }

    /**
     * 메인 배너 이미지 전체 조회
     */
    @Override
    public List<ResponseMainBannerImageDto> getAllMainBannerImage() {
        return mainBannerImageRepository.findByDeletedFalse()
                .stream()
                .map(ResponseMainBannerImageDto::from)
                .toList();
    }

    /**
     * 메인 배너 이미지 수정
     * @param requestUpdateMainBannerImageDto
     */
    @Transactional
    @Override
    public void updateMainBannerImage(RequestUpdateMainBannerImageDto requestUpdateMainBannerImageDto) {
        MainBannerImage mainBannerImage = mainBannerImageRepository.findByMainBannerImageUuid(requestUpdateMainBannerImageDto.getMainBannerImageUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_MAIN_BANNER));
        mainBannerImageRepository.save(requestUpdateMainBannerImageDto.updateEntity(mainBannerImage));
    }

    /**
     * 메인 배너 이미지 삭제
     * @param requestDeleteMainBannerImageDto
     */
    @Transactional
    @Override
    public void deleteMainBannerImage(RequestDeleteMainBannerImageDto requestDeleteMainBannerImageDto) {
        MainBannerImage mainBannerImage = mainBannerImageRepository.findByMainBannerImageUuid(requestDeleteMainBannerImageDto.getMainBannerImageUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_MAIN_BANNER));
        mainBannerImage.softDelete();
    }
}
