package com.starbucks.back.review.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewPhoto extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 리뷰 uuid
     */
    @Column(name = "review_uuid", length = 50, nullable = false)
    private String reviewUuid;

    /**
     * 리뷰 사진
     */
    @Column(name = "image_url")
    private String imageUrl;

    /**
     * 리뷰 id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @Builder
    public ReviewPhoto(Long id, String reviewUuid, String imageUrl, Review review) {
        this.id = id;
        this.reviewUuid = reviewUuid;
        this.imageUrl = imageUrl;
        this.review = review;
    }

}
