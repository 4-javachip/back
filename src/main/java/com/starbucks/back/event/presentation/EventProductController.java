package com.starbucks.back.event.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.event.application.EventProductService;
import com.starbucks.back.event.dto.in.RequestAddEventProductDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventProductDto;
import com.starbucks.back.event.dto.out.ResponseEventProductDto;
import com.starbucks.back.event.vo.in.RequestAddEventProductVo;
import com.starbucks.back.event.vo.in.RequestDeleteEventProductVo;
import com.starbucks.back.event.vo.out.ResponseEventProductVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/event-product")
@RestController
@RequiredArgsConstructor
public class EventProductController {

    private final EventProductService eventProductService;

    /**
     * 기획전 상품 추가
     * @param requestAddEventProductVo
     */
    @Operation(summary = "기획전 상품 추가 API", description = "기획전 상품 추가 API 입니다.", tags = {"Event-Product-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addEventProduct(@RequestBody RequestAddEventProductVo requestAddEventProductVo) {
        eventProductService.addEventProduct(RequestAddEventProductDto.from(requestAddEventProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 기획전 상품 id로 기획전 상품 조회
     * @param id
     */
    @Operation(summary = "기획전 상품 id로 기획전 상품 조회 API", description = "기획전 상품 id로 기획전 상품 조회 API 입니다.", tags = {"Event-Product-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseEventProductVo> getEventProductById(@PathVariable("id") Long id) {
        ResponseEventProductDto responseEventProductDto = eventProductService.getEventProductById(id);
        return new BaseResponseEntity<>(responseEventProductDto.toVo());
    }

    /**
     * 상품 uuid로 기획전 상품 조회
     * @param productUuid
     */
    @Operation(summary = "상품 uuid로 기획전 상품 조회 API", description = "상품 uuid로 기획전 상품 조회 API 입니다.", tags = {"Event-Product-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<List<ResponseEventProductVo>> getEventProductByProductUuid(@RequestParam("productUuid") String productUuid) {
        List<ResponseEventProductVo> result = eventProductService.getEventProductByProductUuid(productUuid)
                .stream()
                .map(ResponseEventProductDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 기획전 uuid로 기획전 상품 리스트 조회
     * @param eventUuid
     */
    @Operation(summary = "기획전 uuid로 기획전 상품 리스트 조회 API", description = "기획전 uuid로 기획전 상품 리스트 조회 API 입니다.", tags = {"Event-Product-Service"})
    @GetMapping("/list/{eventUuid}")
    public BaseResponseEntity<List<ResponseEventProductVo>> getEventProductByEventUuid(@PathVariable("eventUuid") String eventUuid) {
        List<ResponseEventProductVo> result = eventProductService.getEventProductByEventUuid(eventUuid)
                .stream()
                .map(ResponseEventProductDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 삭제되지 않은 기획전 상품 리스트 전체 조회
     */
    @Operation(summary = "삭제되지 않은 기획전 상품 리스트 전체 조회 API", description = "삭제되지 않은 기획전 상품 리스트 전체 조회 API 입니다.", tags = {"Event-Product-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseEventProductVo>> getAllEventProducts() {
        List<ResponseEventProductVo> result = eventProductService.getAllEventProducts()
                .stream()
                .map(ResponseEventProductDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 기획전 상품 삭제
     */
    @Operation(summary = "기획전 상품 삭제 API", description = "기획전 상품 삭제 API 입니다.", tags = {"Event-Product-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteEventProduct(@RequestBody RequestDeleteEventProductVo requestDeleteEventProductVo) {
        eventProductService.deleteEventProduct(RequestDeleteEventProductDto.of(requestDeleteEventProductVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
