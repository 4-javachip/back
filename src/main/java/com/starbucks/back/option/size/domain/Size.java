package com.starbucks.back.option.size.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "size_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Size extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사이즈
     */
    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public Size(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
