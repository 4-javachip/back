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
