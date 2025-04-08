package com.starbucks.back.banner.application;

import com.starbucks.back.banner.dto.in.RequestAddMainBannerImageDto;
import com.starbucks.back.banner.dto.in.RequestDeleteMainBannerImageDto;
import com.starbucks.back.banner.dto.in.RequestUpdateMainBannerImageDto;
import com.starbucks.back.banner.dto.out.ResponseMainBannerImageDto;

import java.util.List;

public interface MainBannerImageService {

    /**
     * 메인 배너 이미지 추가
     * @param requestAddMainBannerImageDto
     */
    void addMainBannerImage(RequestAddMainBannerImageDto requestAddMainBannerImageDto);

    /**
     * 메인 배너 이미지 uuid로 메인 배너 이미지 조회
     * @param mainBannerImageUuid
     */
    ResponseMainBannerImageDto getMainBannerImageByMainBannerImageUuid(String mainBannerImageUuid);

    /**
     * 메인 배너 이미지 전체 조회
     */
    List<ResponseMainBannerImageDto> getAllMainBannerImage();

    /**
     * 메인 배너 이미지 수정
     * @param requestUpdateMainBannerImageDto
     */
    void updateMainBannerImage(RequestUpdateMainBannerImageDto requestUpdateMainBannerImageDto);

    /**
     * 메인 배너 이미지 삭제
     * @param requestDeleteMainBannerImageDto
     */
    void deleteMainBannerImage(RequestDeleteMainBannerImageDto requestDeleteMainBannerImageDto);

}
