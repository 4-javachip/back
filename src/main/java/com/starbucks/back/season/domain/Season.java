package com.starbucks.back.season.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "season")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Season extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    /**
     * 시즌 이름
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Builder
    public Season(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
