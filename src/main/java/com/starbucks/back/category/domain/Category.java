package com.starbucks.back.category.domain;

import com.starbucks.back.common.entity.BaseEntity;
import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 카테고리 이름
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * 카테고리 이미지
     */
    @Column(name = "image", nullable = false)
    private String image;

    /**
     * 카테고리 설명
     */
    @Column(name = "description", nullable = false)
    private String description;

    @Builder
    public Category (Long id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
    }

}
