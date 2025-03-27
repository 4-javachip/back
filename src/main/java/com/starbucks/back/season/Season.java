package com.starbucks.back.season;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "season")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Season {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    /**
     * 시즌 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public Season(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
