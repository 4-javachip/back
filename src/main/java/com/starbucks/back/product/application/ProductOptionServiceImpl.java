package com.starbucks.back.product.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.product.domain.ProductOption;
import com.starbucks.back.product.dto.in.RequestAddProductOptionDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductOptionDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductOptionDto;
import com.starbucks.back.product.dto.out.ResponseProductOptionDto;
import com.starbucks.back.product.infrastructure.ProductOptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    /**
     * 상품 옵션 추가
     * @param requestAddProductOptionDto
     */
    @Transactional
    @Override
    public void addProductOption(RequestAddProductOptionDto requestAddProductOptionDto) {
        productOptionRepository.save(requestAddProductOptionDto.toEntity());
    }

    /**
     * 상품 옵션 UUID로 조회
     * @param productOptionUuid
     */
    @Override
    public ResponseProductOptionDto getProductOptionByProductOptionUuid(String productOptionUuid) {
        ProductOption productOption = productOptionRepository.findByProductOptionUuid(productOptionUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT_OPTION));
        return ResponseProductOptionDto.from(productOption);
    }

    /**
     * 상품 UUID로 최저가인 상품 옵션 조회
     * @param productUuid
     */
    @Override
    public ResponseProductOptionDto getProductOptionByProductUuidOrderByTotalPriceAsc(String productUuid) {
        ProductOption productOption = productOptionRepository.findTop1ByProductUuidOrderByTotalPriceAsc(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT_OPTION));
        return ResponseProductOptionDto.from(productOption);
    }

    /**
     * 상품 UUID로 상품 옵션 리스트 조회
     * @param productUuid
     */
    @Override
    public List<ResponseProductOptionDto> getProductOptionsByProductUuid(String productUuid) {
        return productOptionRepository.findByProductUuidAndDeletedFalse(productUuid)
                .stream()
                .map(ResponseProductOptionDto::from)
                .toList();
    }

    /**
     * 상품 옵션 리스트 전체 조회
     */
    @Override
    public List<ResponseProductOptionDto> getAllProductOptions() {
        return productOptionRepository.findAll()
                .stream()
                .map(ResponseProductOptionDto::from)
                .toList();
    }

    /**
     * 상품 옵션 수정
     * @param requestUpdateProductOptionDto
     */
    @Transactional
    @Override
    public void updateProductOption(RequestUpdateProductOptionDto requestUpdateProductOptionDto) {
        ProductOption productOption = productOptionRepository.findByProductOptionUuid(requestUpdateProductOptionDto.getProductOptionUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT_OPTION));
        productOptionRepository.save(requestUpdateProductOptionDto.updateEntity(productOption));
    }

    /**
     * 상품 옵션 삭제
     * @param requestDeleteProductOptionDto
     */
    @Transactional
    @Override
    public void deleteProductOption(RequestDeleteProductOptionDto requestDeleteProductOptionDto) {
        ProductOption productOption = productOptionRepository.findByProductOptionUuid(requestDeleteProductOptionDto.getProductOptionUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT_OPTION));
        productOption.softDelete();
    }
}
