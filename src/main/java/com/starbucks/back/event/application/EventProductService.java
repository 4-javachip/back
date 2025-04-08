package com.starbucks.back.event.application;

import com.starbucks.back.event.dto.in.RequestAddEventProductDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventProductDto;
import com.starbucks.back.event.dto.out.ResponseEventProductDto;

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
    List<ResponseEventProductDto> getEventProductByEventUuid(String eventUuid);

    /**
     * 삭제되지 않은 기획전 상품 리스트 전체 조회
     * TODO : 페이징 처리 필요(관리자 입장에서 보기 위해)
     */
    List<ResponseEventProductDto> getAllEventProducts();

    /**
     * 기획전 상품 삭제
     * @param requestDeleteEventProductDto
     */
    void deleteEventProduct(RequestDeleteEventProductDto requestDeleteEventProductDto);

}
