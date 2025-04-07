package com.starbucks.back.option.color.domain;

import com.starbucks.back.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "color_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Color extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 색상 이름
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Builder
    public Color(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
