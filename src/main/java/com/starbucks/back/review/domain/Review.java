package com.starbucks.back.review.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 유저 uuid
     */
    @Column(name = "user_uuid", length = 50, nullable = false)
    private String userUuid;

    /**
     * 상품 uuid
     */
    @Column(name = "product_uuid", length = 50, nullable = false)
    private String productUuid;

    /**
     * 리뷰 uuid
     */
    @Column(name = "review_uuid", length = 50, nullable = false, unique = true)
    private String reviewUuid;

    /**
     * 리뷰 제목
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 리뷰 내용
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * 별점
     */
    @Column(name = "rating", nullable = false)
    private Integer rating;

    /**
     * 주문 상품 uuid
     */
    @Column(name = "order_detail_uuid", nullable = false)
    private String orderDetailUuid;

    @Builder
    public  Review(Long id, String userUuid, String productUuid, String reviewUuid,
                   String title, String content, Integer rating, String orderDetailUuid) {
        this.id = id;
        this.userUuid = userUuid;
        this.productUuid = productUuid;
        this.reviewUuid = reviewUuid;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.orderDetailUuid = orderDetailUuid;
    }

}
