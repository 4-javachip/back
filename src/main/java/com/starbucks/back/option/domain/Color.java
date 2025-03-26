package com.starbucks.back.option.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "color_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Color extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 색상 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public Color(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
