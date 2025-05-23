package com.starbucks.back.event.domain;

import com.starbucks.back.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "event")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_uuid", length = 50, nullable = false, unique = true)
    private String eventUuid;

    /**
     * 기획전 이름
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 기획전 이미지 URL
     */
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    /**
     * 기획전 설명
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * 기획전 유의사항 / 지침
     */
    @Column(name = "precaution", nullable = false)
    private String precaution;

    /**
     * 시작 시간
     */
    @Column(name = "start_at", columnDefinition = "DATETIME(0)", nullable = false)
    private LocalDate startAt;

    /**
     * 종료 시간
     */
    @Column(name = "end_at", columnDefinition = "DATETIME(0)", nullable = false)
    private LocalDate endAt;

    /**
     * 활성화 여부
     */
    @Column(name = "state", nullable = false)
    private Boolean state = false;

    public void updateState(Boolean state) {
        this.state = state;
    }

    @Builder
    public Event(Long id, String eventUuid, String name, String imageUrl, String description,
                 String precaution, LocalDate startAt, LocalDate endAt, Boolean state) {
        this.id = id;
        this.eventUuid = eventUuid;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.precaution = precaution;
        this.startAt = startAt;
        this.endAt = endAt;
        this.state = state;
    }

}
