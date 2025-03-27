package com.starbucks.back.banner.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_banner_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainBannerImage extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    /**
     * 메인 배너 이미지 Uuid
     */
    @Column(name = "main_banner_image_uuid", length = 50, nullable = false)
    private String mainBannerImageUuid;

    /**
     * 이벤트 uuid
     */
    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    /**
     * 이미지 URL
     */
    @Column(name = "image_url", nullable = false)
    private String ImageUrl;

    /**
     * 이미지 설명
     */
    @Column(name = "description")
    private String description;

    @Builder
    public MainBannerImage(Long id, String mainBannerImageUuid, String eventUuid,
                           String imageUrl, String description) {
        this.id = id;
        this.mainBannerImageUuid = mainBannerImageUuid;
        this.eventUuid = eventUuid;
        this.ImageUrl = imageUrl;
        this.description = description;
    }

}
