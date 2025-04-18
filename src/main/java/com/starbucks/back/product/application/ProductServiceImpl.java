package com.starbucks.back.product.application;

import com.starbucks.back.best.application.BestService;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.CursorPageUtil;
import com.starbucks.back.product.domain.Product;
import com.starbucks.back.product.domain.ProductSortType;
import com.starbucks.back.product.dto.in.RequestAddProductDto;
import com.starbucks.back.product.dto.in.RequestDeleteProductDto;
import com.starbucks.back.product.dto.in.RequestUpdateProductDto;
import com.starbucks.back.product.dto.out.ResponseProductDto;
import com.starbucks.back.product.infrastructure.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BestService bestService;

    /**
     * 상품 추가
     */
    @Transactional
    @Override
    public void addProduct(RequestAddProductDto requestAddProductDto) {
        if (productRepository.existsByName(requestAddProductDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_PRODUCT);
        }
        productRepository.save(requestAddProductDto.toEntity());
    }

    /**
     * 상품 이름으로 조회
     */
    @Override
    public ResponseProductDto getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return ResponseProductDto.from(product);
    }

    /**
     * 상품 UUID로 조회
     */
    @Override
    public ResponseProductDto getProductByUuid(String productUuid) {
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return ResponseProductDto.from(product);
    }

    /**
     * 상품 필터링 조회
     */
    @Override
    public CursorPageUtil<ResponseProductDto, Long> getAllProductsByFilter(
            Long categoryId,
            Long subCategoryId,
            Long seasonId,
            ProductSortType sortType,
            String keyword,
            Long cursor,
            Integer pageSize,
            Integer page) {

        return productRepository.findByFilterWithPagination(
                categoryId,
                subCategoryId,
                seasonId,
                sortType,
                keyword,
                cursor,
                pageSize,
                page
        );
    }

    /**
     * 상품 수정
     */
    @Transactional
    @Override
    public void updateProduct(RequestUpdateProductDto requestUpdateProductDto) {
        Product product = productRepository.findByProductUuid(requestUpdateProductDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        productRepository.save(requestUpdateProductDto.updateEntity(product));
    }

    /**
     * 상품 삭제
     */
    @Transactional
    @Override
    public void deleteProduct(RequestDeleteProductDto requestDeleteProductDto) {
        Product product = productRepository.findByProductUuid(requestDeleteProductDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        productRepository.delete(product);
    }

    /**
     * 상품 베스트 상품 상태 업데이트
     */
    @Transactional
    @Override
    public void updateBestProductStatus() {
        // 전체 product best = false 초기화
        productRepository.updateAllBestFalse();

        // 상위 30개 UUID 조회
        Set<String> top30 = bestService.getTop30BestProductUuids();

        // 해당 UUID만 best = true로 업데이트
        productRepository.updateBestTrueByProductUuids(top30);
    }

}
