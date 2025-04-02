package com.starbucks.back.product.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "thumbnail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Thumbnail extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false)
    private String productUuid;

    /**
     * 썸네일 이미지
     */
    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    /**
     * 썸네일 이미지 설명
     */
    @Column(name = "description")
    private String description;

    @Builder
    public Thumbnail(Long id, String productUuid, String thumbnailUrl, String description) {
        this.id = id;
        this.productUuid = productUuid;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
    }

}
