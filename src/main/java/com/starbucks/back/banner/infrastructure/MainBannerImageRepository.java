package com.starbucks.back.banner.infrastructure;

import com.starbucks.back.banner.domain.MainBannerImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MainBannerImageRepository extends JpaRepository<MainBannerImage, Long> {

    /**
     * 메인 배너 이미지 리스트 전체 조회
     */
    List<MainBannerImage> findByDeletedFalse();

    /**
     * 메인 배너 이미지 UUID로 메인 배너 이미지 조회
     */
    Optional<MainBannerImage> findByMainBannerImageUuid(String mainBannerImageUuid);

}
