package com.starbucks.back.category.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sub_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubCategory extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 서브 카테고리 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 카테고리(부모)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    public SubCategory(Long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

}
