package com.starbucks.back.option.domain;

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
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 색상 이름
     */
    @Column(nullable = false)
    private String name;

    @Builder
    public Color(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
