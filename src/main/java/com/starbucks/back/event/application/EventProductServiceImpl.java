package com.starbucks.back.event.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.event.domain.EventProduct;
import com.starbucks.back.event.dto.in.RequestAddEventProductDto;
import com.starbucks.back.event.dto.in.RequestDeleteEventProductDto;
import com.starbucks.back.event.dto.out.ResponseEventProductDto;
import com.starbucks.back.event.infrastructure.EventProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventProductServiceImpl implements EventProductService{

    private final EventProductRepository eventProductRepository;

    /**
     * 기획전 상품 추가
     * @param requestAddEventProductDto
     */
    @Transactional
    @Override
    public void addEventProduct(RequestAddEventProductDto requestAddEventProductDto) {
        if (eventProductRepository.existsByEventUuidAndProductUuidAndDeletedFalse(requestAddEventProductDto.getEventUuid(), requestAddEventProductDto.getProductUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_PRODUCT);
        }
        eventProductRepository.save(requestAddEventProductDto.toEntity());
    }

    /**
     * 기획전 상품 id로 기획전 상품 조회
     * @param id
     */
    @Override
    public ResponseEventProductDto getEventProductById(Long id) {
        EventProduct eventProduct = eventProductRepository.findById(id).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return ResponseEventProductDto.from(eventProduct);
    }

    /**
     * 상품 uuid로 기획전 상품 조회
     * @param productUuid
     */
    @Override
    public List<ResponseEventProductDto> getEventProductByProductUuid(String productUuid) {
        return eventProductRepository.findByProductUuidAndDeletedFalse(productUuid)
                .stream()
                .map(ResponseEventProductDto::from)
                .toList();
    }

    /**
     * 기획전 uuid로 기획전 상품 리스트 조회
     * @param eventUuid
     */
    @Override
    public List<ResponseEventProductDto> getEventProductByEventUuid(String eventUuid) {
        return eventProductRepository.findByEventUuidAndDeletedFalse(eventUuid)
                .stream()
                .map(ResponseEventProductDto::from)
                .toList();
    }

    /**
     * 삭제되지 않은 기획전 상품 리스트 조회
     */
    @Override
    public List<ResponseEventProductDto> getAllEventProducts() {
        return eventProductRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseEventProductDto::from)
                .toList();
    }

    /**
     * 기획전 상품 삭제
     * @param requestDeleteEventProductDto
     */
    @Transactional
    @Override
    public void deleteEventProduct(RequestDeleteEventProductDto requestDeleteEventProductDto) {
        EventProduct eventProduct = eventProductRepository.findByProductUuidAndDeletedFalse(requestDeleteEventProductDto.getProductUuid())
                .stream()
                .findFirst()
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        eventProduct.softDelete();
    }

}
