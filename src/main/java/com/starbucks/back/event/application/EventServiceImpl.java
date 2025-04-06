package com.starbucks.back.event.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.event.domain.Event;
import com.starbucks.back.event.dto.in.RequestAddEventDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventDto;
import com.starbucks.back.event.dto.in.RequestUpdateEventDto;
import com.starbucks.back.event.dto.out.ResponseEventDto;
import com.starbucks.back.event.infrastructure.EventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    /**
     * 이벤트 추가
     * @param requestAddEventDto
     */
    @Transactional
    @Override
    public void addEvent(RequestAddEventDto requestAddEventDto) {
        eventRepository.save(requestAddEventDto.toEntity());
    }

    /**
     * 이벤트 UUID로 이벤트 조회
     * @param eventUuid
     */
    @Override
    public ResponseEventDto getEventByEventUuid(String eventUuid) {
        Event event = eventRepository.findByEventUuidAndDeletedFalse(eventUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
        return ResponseEventDto.from(event);
    }

    /**
     * 이벤트 전체 조회
     */
    @Override
    public List<ResponseEventDto> getAllEvents() {
        return eventRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseEventDto::from)
                .toList();
    }

    /**
     * 기획전 수정
     * @param requestUpdateEventDto
     */
    @Transactional
    @Override
    public void updateEvent(RequestUpdateEventDto requestUpdateEventDto) {
        Event event = eventRepository.findByEventUuidAndDeletedFalse(requestUpdateEventDto.getEventUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
        eventRepository.save(requestUpdateEventDto.updateEntity(event));
    }

    /**
     * 기획전 삭제
     * @param requestDeleteEventDto
     */
    @Transactional
    @Override
    public void deleteEvent(RequestDeleteEventDto requestDeleteEventDto) {
        Event event = eventRepository.findByEventUuidAndDeletedFalse(requestDeleteEventDto.getEventUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
        event.softDelete();
    }
}
