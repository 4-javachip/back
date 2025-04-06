package com.starbucks.back.event.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.event.application.EventService;
import com.starbucks.back.event.dto.in.RequestAddEventDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventDto;
import com.starbucks.back.event.dto.in.RequestUpdateEventDto;
import com.starbucks.back.event.dto.out.ResponseEventDto;
import com.starbucks.back.event.vo.in.RequestAddEventVo;
import com.starbucks.back.event.vo.in.RequestDeleteEventVo;
import com.starbucks.back.event.vo.in.RequestEventVo;
import com.starbucks.back.event.vo.out.ResponseEventVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/event")
@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * 기획전 추가
     * @param requestAddEventVo
     */
    @Operation(summary = "기획전 추가 API", description = "기획전 추가 API 입니다.", tags = {"Event-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addEvent(@RequestBody RequestAddEventVo requestAddEventVo) {
        eventService.addEvent(RequestAddEventDto.from(requestAddEventVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 기획전 UUID로 기획전 조회
     * @param eventUuid
     */
    @Operation(summary = "기획전 UUID로 기획전 조회 API", description = "기획전 UUID로 기획전 조회 API 입니다.", tags = {"Event-Service"})
    @GetMapping("/{eventUuid}")
    public BaseResponseEntity<ResponseEventVo> getEventByEventUuid(@PathVariable String eventUuid) {
        ResponseEventDto responseEventDto = eventService.getEventByEventUuid(eventUuid);
        return new BaseResponseEntity<>(responseEventDto.toVo());
    }

    /**
     * 기획전 전체 조회
     */
    @Operation(summary = "기획전 전체 조회 API", description = "기획전 전체 조회 API 입니다.", tags = {"Event-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseEventVo>> getAllEvents() {
        List<ResponseEventDto> responseEventDto = eventService.getAllEvents();
        return new BaseResponseEntity<>(responseEventDto.stream()
                .map(ResponseEventDto::toVo)
                .collect(Collectors.toList()));
    }

    /**
     * 기획전 수정
     * @param requestEventVo
     */
    @Operation(summary = "기획전 수정 API", description = "기획전 수정 API 입니다.", tags = {"Event-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateEvent(@RequestBody RequestEventVo requestEventVo) {
        eventService.updateEvent(RequestUpdateEventDto.from(requestEventVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 기획전 삭제
     * @param requestDeleteEventVo
     */
    @Operation(summary = "기획전 삭제 API", description = "기획전 삭제 API 입니다.", tags = {"Event-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteEvent(@RequestBody RequestDeleteEventVo requestDeleteEventVo) {
        eventService.deleteEvent(RequestDeleteEventDto.of(requestDeleteEventVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
