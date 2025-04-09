package com.starbucks.back.event.dto.in;

import com.starbucks.back.event.domain.Event;
import com.starbucks.back.event.vo.in.RequestEventVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RequestUpdateEventDto {

    private String eventUuid;
    private String name;
    private String imageUrl;
    private String description;
    private String precaution;
    private LocalDate startAt;
    private LocalDate endAt;
    private Boolean state;

    @Builder
    public RequestUpdateEventDto(String eventUuid, String imageUrl, String name, String description, String precaution, LocalDate startAt, LocalDate endAt, Boolean state) {
        this.eventUuid = eventUuid;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.precaution = precaution;
        this.startAt = startAt;
        this.endAt = endAt;
        this.state = state;
    }

    public Event updateEntity(Event event) {
        return Event.builder()
                .id(event.getId())
                .eventUuid(eventUuid)
                .name(name)
                .imageUrl(imageUrl)
                .description(description)
                .precaution(precaution)
                .startAt(startAt)
                .endAt(endAt)
                .state(state)
                .build();
    }

    public static RequestUpdateEventDto from(RequestEventVo requestEventVo) {
        return RequestUpdateEventDto.builder()
                .eventUuid(requestEventVo.getEventUuid())
                .name(requestEventVo.getName())
                .imageUrl(requestEventVo.getImageUrl())
                .description(requestEventVo.getDescription())
                .precaution(requestEventVo.getPrecaution())
                .startAt(requestEventVo.getStartAt())
                .endAt(requestEventVo.getEndAt())
                .state(requestEventVo.getState())
                .build();
    }

}
