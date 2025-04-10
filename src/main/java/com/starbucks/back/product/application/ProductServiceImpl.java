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
     * @param requestAddProductDto
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
     * @param name
     */
    @Override
    public ResponseProductDto getProductByName(String name) {
        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        return ResponseProductDto.from(product);
    }

    /**
     * 상품 UUID로 조회
     * @param productUuid
     */
    @Override
    public ResponseProductDto getProductByUuid(String productUuid) {
        Product product = productRepository.findByProductUuid(productUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));

        Set<String> top30 = bestService.getTop30BestProductUuids();
        boolean isBest = top30.contains(productUuid);

        return ResponseProductDto.of(product, isBest);
    }

    /**
     * 상품 필터링 조회
     * @param categoryId
     * @param subCategoryId
     * @param seasonId
     * @param sortType
     * @param cursor
     * @param pageSize
     * @param page
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
        Set<String> bestUuids = bestService.getTop30BestProductUuids();
        return productRepository.findByFilterWithPagination(
                categoryId,
                subCategoryId,
                seasonId,
                sortType,
                keyword,
                cursor,
                pageSize,
                page,
                bestUuids
        );
    }

    /**
     * 상품 수정
     * @param requestUpdateProductDto
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
     * @param requestDeleteProductDto
     */
    @Transactional
    @Override
    public void deleteProduct(RequestDeleteProductDto requestDeleteProductDto) {
        Product product = productRepository.findByProductUuid(requestDeleteProductDto.getProductUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PRODUCT));
        productRepository.delete(product);
    }
}
