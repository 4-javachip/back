package com.starbucks.back.event.infrastructure;

import com.starbucks.back.event.domain.Event;
import com.starbucks.back.event.dto.out.ResponseEventDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * 기획전 UUID로 기획전 조회
     * @param eventUuid
     */
    Optional<Event> findByEventUuidAndDeletedFalse(String eventUuid);

    /**
     * 삭제되지 않은 기획전 리스트 조회
     */
    List<Event> findAllByDeletedFalse();

}
