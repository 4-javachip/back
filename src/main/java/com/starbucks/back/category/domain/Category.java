package com.starbucks.back.category.domain;

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
public class Category extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 카테고리 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 카테고리 이미지
     */
    @Column(name = "image", nullable = false)
    private String image;

    @Builder
    public Category (Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

}
