package com.starbucks.back.product.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.product.domain.ProductCategoryList;
import com.starbucks.back.product.dto.in.RequestAddProductCategoryListDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductCategoryListDto;
import com.starbucks.back.product.dto.out.ResponseProductCategoryListDto;
import com.starbucks.back.product.infrastructure.ProductCategoryListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;

    /**
     * 상품 카테고리 리스트 추가
     * @param requestAddProductCategoryListDto
     */
    @Transactional
    @Override
    public void addProductCategoryList(RequestAddProductCategoryListDto requestAddProductCategoryListDto) {
        productCategoryListRepository.save(requestAddProductCategoryListDto.toEntity());
    }

    /**
     * 상품 uuid로 상품 카테고리 리스트 조회
     * @param productUuid
     */
    @Override
    public ResponseProductCategoryListDto getProductCategoryListByProductUuid(String productUuid) {
        ProductCategoryList productCategoryList = productCategoryListRepository.findByProductUuidAndDeletedFalse(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        return ResponseProductCategoryListDto.from(productCategoryList);
    }

    /**
     * 상품 카테고리 리스트 전체 조회
     */
    @Override
    public List<ResponseProductCategoryListDto> getAllProductCategoryList() {
        return productCategoryListRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseProductCategoryListDto::from)
                .toList();
    }

    /**
     * 상품 카테고리 리스트 삭제
     * @param requestDeleteProductCategoryListDto
     */
    @Transactional
    @Override
    public void deleteProductCategoryList(RequestDeleteProductCategoryListDto requestDeleteProductCategoryListDto) {
        ProductCategoryList productCategoryList = productCategoryListRepository.findByProductUuidAndDeletedFalse(requestDeleteProductCategoryListDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CATEGORY));
        productCategoryList.softDelete();
    }

}
