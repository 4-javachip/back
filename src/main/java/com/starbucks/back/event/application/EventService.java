package com.starbucks.back.event.application;

import com.starbucks.back.event.dto.in.RequestAddEventDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventDto;
import com.starbucks.back.event.dto.in.RequestUpdateEventDto;
import com.starbucks.back.event.dto.out.ResponseEventDto;

import java.util.List;

public interface EventService {

    /**
     * 기획전 추가
     * @param requestAddEventDto
     */
    void addEvent(RequestAddEventDto requestAddEventDto);

    /**
     * 이벤트 UUID로 삭제되지 않은 기획전 조회
     * @param eventUuid
     */
    ResponseEventDto getEventByEventUuid(String eventUuid);

    /**
     * 삭제되지 않은 이벤트 기획전 조회
     */
    List<ResponseEventDto> getAllEvents();

    /**
     * 기획전 수정
     * @param requestUpdateEventDto
     */
    void updateEvent(RequestUpdateEventDto requestUpdateEventDto);

    /**
     * 기획전 삭제
     * @param requestDeleteEventDto
     */
    void deleteEvent(RequestDeleteEventDto requestDeleteEventDto);

}
