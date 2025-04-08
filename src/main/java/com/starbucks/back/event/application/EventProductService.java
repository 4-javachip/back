package com.starbucks.back.event.application;

import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.event.dto.in.RequestAddEventProductDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventProductDto;
import com.starbucks.back.event.dto.out.ResponseEventProductDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventProductService {

    /**
     * 기획전 상품 등록
     * @param requestAddEventProductDto
     */
    void addEventProduct(RequestAddEventProductDto requestAddEventProductDto);

    /**
     * id로 기획전 상품 조회
     * @param id
     */
    ResponseEventProductDto getEventProductById(Long id);

    /**
     * 상품 UUID로 기획전 상품 조회
     * @param productUuid
     */
    List<ResponseEventProductDto> getEventProductByProductUuid(String productUuid);

    /**
     * 기획전 uuid로 삭제되지 않은 기획전 상품 리스트 조회
     * TODO : 페이징 처리 필요
     */
    CursorPageUtil<ResponseEventProductDto, Long> getEventProductByEventUuid(String eventUuid, Long lastId);

    /**
     * 삭제되지 않은 기획전 상품 리스트 전체 조회
     * 관리자 페이지
     * TODO : 페이징 처리 필요(관리자 입장에서 보기 위해)
     */
    CursorPageUtil<ResponseEventProductDto, Long> getAllEventProducts(Long lastId);

    /**
     * 기획전 상품 삭제
     * @param requestDeleteEventProductDto
     */
    void deleteEventProduct(RequestDeleteEventProductDto requestDeleteEventProductDto);

}
