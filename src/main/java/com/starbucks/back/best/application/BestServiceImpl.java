package com.starbucks.back.best.application;

import com.starbucks.back.best.domain.Best;
import com.starbucks.back.best.dto.in.RequestAddBestDto;
import com.starbucks.back.best.dto.in.RequestDeleteBestDto;
import com.starbucks.back.best.dto.in.RequestUpdateBestDto;
import com.starbucks.back.best.dto.out.ResponseBestDto;
import com.starbucks.back.best.infrastructure.BestRepository;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BestServiceImpl implements BestService {

    private final BestRepository bestRepository;

    /**
     * 베스트 상품 추가
     * @param requestAddBestDto
     */
    @Transactional
    @Override
    public void addBestProduct(RequestAddBestDto requestAddBestDto) {
        bestRepository.save(requestAddBestDto.toEntity());
    }

    /**
     * 베스트 상품 전체 조회 (상위 30개)
     */
    @Override
    public List<ResponseBestDto> getAllBestProducts() {
        return bestRepository.findTop30ByOrderByProductSalesCountDesc()
                .stream()
                .map(ResponseBestDto::from)
                .toList();
    }

    /**
     * 베스트 태그
     */
    @Override
    public Set<String> getTop30BestProductUuids() {
        return bestRepository.findTop30ByOrderByProductSalesCountDesc()
                .stream()
                .map(Best::getProductUuid)
                .collect(Collectors.toSet());
    }

    /**
     * 카테고리별 베스트 상품 리스트 조회
     * @param categoryId
     */
    @Override
    public List<ResponseBestDto> getTop30BestProductsByCategoryId(Long categoryId) {
        return bestRepository.findTop30ByCategoryId(categoryId)
                .stream()
                .map(ResponseBestDto::from)
                .toList();
    }

    /**
     * 베스트 상품 수정
     * @param requestUpdateBestDto
     */
    @Transactional
    @Override
    public void updateBestProduct(RequestUpdateBestDto requestUpdateBestDto) {
        Best best = bestRepository.findByProductUuid(requestUpdateBestDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        bestRepository.save(requestUpdateBestDto.updateEntity(best));
    }

    /**
     * 베스트 상품 삭제
     * @param requestDeleteBestDto
     */
    @Transactional
    @Override
    public void deleteBestProduct(RequestDeleteBestDto requestDeleteBestDto) {
        Best best = bestRepository.findByProductUuid(requestDeleteBestDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        bestRepository.delete(best);
    }
}
