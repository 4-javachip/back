package com.starbucks.back.dummy.application;

import com.starbucks.back.best.domain.Best;
import com.starbucks.back.best.infrastructure.BestRepository;
import com.starbucks.back.product.domain.*;
import com.starbucks.back.product.infrastructure.ProductRepository;
import com.starbucks.back.category.domain.Category;
import com.starbucks.back.category.domain.SubCategory;
import com.starbucks.back.category.infrastructure.CategoryRepository;
import com.starbucks.back.category.infrastructure.SubCategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DummyDataService {

    private final ProductRepository productRepository;
    private final BestRepository bestRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @PersistenceContext
    private EntityManager em;

    /**
     * 더미 데이터 삽입
     */
    @Transactional
    public String generateDummyProducts() {

        // 카테고리 및 서브카테고리 생성
        if (categoryRepository.count() == 0) {
            for (int i = 1; i <= 5; i++) {
                Category category = categoryRepository.save(Category.builder()
                        .name("카테고리 " + i)
                        .build());

                for (int j = 1; j <= 3; j++) {
                    subCategoryRepository.save(SubCategory.builder()
                            .category(category)
                            .name("서브카테고리 " + i + "-" + j)
                            .build());
                }
            }
        }

        List<Long> colorIds = List.of(1L, 2L, 3L);
        List<Long> sizeIds = List.of(1L, 2L, 3L);
        List<Long> categoryIds = categoryRepository.findAll().stream().map(Category::getId).toList();
        Map<Long, List<Long>> subCategoryMap = new HashMap<>();
        for (Long categoryId : categoryIds) {
            List<Long> subIds = subCategoryRepository.findByCategoryId(categoryId).stream().map(SubCategory::getId).toList();
            subCategoryMap.put(categoryId, subIds);
        }

        Random random = new Random();
        int productCount = 50000;

        for (int i = 1; i <= productCount; i++) {
            String productUuid = UUID.randomUUID().toString();

            // Product 저장
            Product product = productRepository.save(Product.builder()
                    .productUuid(productUuid)
                    .name("더미 상품 " + i)
                    .best(i <= 30)
                    .build());

            // ProductOption 삽입 (2~3개), 최소 가격 옵션 따로 추적
            int optionCount = 2 + random.nextInt(2);
            int minPrice = Integer.MAX_VALUE;
            String minPriceOptionUuid = null;

            for (int j = 0; j < optionCount; j++) {
                int price = 500 + random.nextInt(100000 - 500 + 1);
                int discountRate = random.nextInt(30);
                int totalPrice = (int) (price * (1 - discountRate / 100.0));

                String optionUuid = UUID.randomUUID().toString();
                if (price < minPrice) {
                    minPrice = price;
                    minPriceOptionUuid = optionUuid;
                }

                em.persist(ProductOption.builder()
                        .productOptionUuid(optionUuid)
                        .productUuid(productUuid)
                        .colorOptionId(randomFrom(colorIds))
                        .sizeOptionId(randomFrom(sizeIds))
                        .price(price)
                        .discountRate(discountRate)
                        .totalPrice(totalPrice)
                        .stock(50 + random.nextInt(100))
                        .build());
            }

            // Thumbnail 삽입
            int defaultIndex = random.nextInt(optionCount);
            for (int j = 0; j < optionCount; j++) {
                em.persist(Thumbnail.builder()
                        .productUuid(productUuid)
                        .thumbnailUrl("https://dummyimage.com/600x400/000/fff&text=상품+" + i + "+옵션" + j)
                        .defaulted(j == defaultIndex)
                        .build());
            }

            // ProductDescription 삽입
            em.persist(ProductDescription.builder()
                    .productUuid(productUuid)
                    .description("간단 설명 " + i)
                    .detailDescription("상세 설명입니다 " + i)
                    .build());

            // ProductCategoryList 삽입
            Long categoryId = randomFrom(categoryIds);
            Long subCategoryId = randomFrom(subCategoryMap.get(categoryId));
            em.persist(ProductCategoryList.builder()
                    .productUuid(productUuid)
                    .categoryId(categoryId)
                    .subCategoryId(subCategoryId)
                    .build());

            // Best 삽입
            if (i <= 30) {
                bestRepository.save(Best.builder()
                        .productUuid(productUuid)
                        .productSalesCount(random.nextInt(1000))
                        .build());
            }
        }
        return "더미 데이터 " + productCount + "개 삽입 완료";
    }

    @Transactional
    public String deleteAllDummyProducts() {
        @SuppressWarnings("unchecked")
        List<String> uuids = em.createQuery(
                        "select p.productUuid from Product p where p.name like :prefix")
                .setParameter("prefix", "더미 상품%")
                .getResultList();

        int count = uuids.size();
        if (count == 0) return "삭제할 더미 데이터가 없습니다.";

        em.createQuery("delete from ProductOption po where po.productUuid in :uuids")
                .setParameter("uuids", uuids)
                .executeUpdate();

        em.createQuery("delete from Thumbnail t where t.productUuid in :uuids")
                .setParameter("uuids", uuids)
                .executeUpdate();

        em.createQuery("delete from ProductDescription pd where pd.productUuid in :uuids")
                .setParameter("uuids", uuids)
                .executeUpdate();

        em.createQuery("delete from ProductCategoryList pcl where pcl.productUuid in :uuids")
                .setParameter("uuids", uuids)
                .executeUpdate();

        em.createQuery("delete from Best b where b.productUuid in :uuids")
                .setParameter("uuids", uuids)
                .executeUpdate();

        em.createQuery("delete from Product p where p.productUuid in :uuids")
                .setParameter("uuids", uuids)
                .executeUpdate();

        return "더미 데이터 " + count + "개 삭제 완료";
    }

    private <T> T randomFrom(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}