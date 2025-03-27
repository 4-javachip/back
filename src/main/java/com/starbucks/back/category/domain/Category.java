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
     * 서브 카테고리 리스트
     */
    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategoryList;

    @Builder
    public Category (Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
