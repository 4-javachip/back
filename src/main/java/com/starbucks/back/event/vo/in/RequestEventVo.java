package com.starbucks.back.event.vo.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RequestEventVo {

    private String eventUuid;
    private String name;
    private String description;
    private String precaution;
    private LocalDate startAt;
    private LocalDate endAt;
    private Boolean state;



}
