package com.starbucks.back.product.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.product.domain.Product;
import com.starbucks.back.product.domain.ProductDescription;
import com.starbucks.back.product.dto.in.RequestAddProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDescriptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDescriptionDto;
import com.starbucks.back.product.dto.out.ResponseProductDescriptionDto;
import com.starbucks.back.product.infrastructure.ProductDescriptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

    private final ProductDescriptionRepository productDescriptionRepository;

    /**
     * 상품 설명 추가
     * @param requestAddProductDescriptionDto
     */
    @Transactional
    @Override
    public void addProductDescription(RequestAddProductDescriptionDto requestAddProductDescriptionDto) {
        productDescriptionRepository.save(requestAddProductDescriptionDto.toEntity());
    }

    /**
     * 상품 설명 상품 UUID로 조회
     * @param productUuid
     */
    @Override
    public ResponseProductDescriptionDto getProductDescriptionByProductUuid(String productUuid) {
        ProductDescription productDescription = productDescriptionRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return ResponseProductDescriptionDto.from(productDescription);
    }

    /**
     * 상품 설명 전체 조회
     */
    @Override
    public List<ResponseProductDescriptionDto> getAllProductDescription() {
        return productDescriptionRepository.findAll()
                .stream()
                .map(ResponseProductDescriptionDto::from)
                .toList();
    }

    /**
     * 상품 설명 수정
     * @param requestUpdateProductDescriptionDto
     */
    @Transactional
    @Override
    public void updateProductDescription(RequestUpdateProductDescriptionDto requestUpdateProductDescriptionDto) {
        ProductDescription productDescription = productDescriptionRepository.findByProductUuid(requestUpdateProductDescriptionDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        productDescriptionRepository.save(requestUpdateProductDescriptionDto.updateEntity(productDescription));
    }

    /**
     * 상품 설명 삭제
     * @param requestDeleteProductDescriptionDto
     */
    @Transactional
    @Override
    public void deleteProductDescription(RequestDeleteProductDescriptionDto requestDeleteProductDescriptionDto) {
        ProductDescription productDescription = productDescriptionRepository.findByProductUuid(requestDeleteProductDescriptionDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        productDescriptionRepository.delete(productDescription);
    }
}
