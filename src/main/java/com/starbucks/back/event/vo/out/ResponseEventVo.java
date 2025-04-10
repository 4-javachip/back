package com.starbucks.back.event.vo.out;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ResponseEventVo {

    private String eventUuid;
    private String name;
    private String imageUrl;
    private String description;
    private String precaution;
    private LocalDate startAt;
    private LocalDate endAt;
    private Boolean state;

    @Builder
    public ResponseEventVo(String eventUuid, String name, String imageUrl, String description, String precaution, LocalDate startAt, LocalDate endAt, Boolean state) {
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
