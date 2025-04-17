package com.starbucks.back.review.vo.out;

import com.starbucks.back.review.dto.out.ResponseReviewDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseReviewPageVo {

    private List<ResponseReviewDto> content;
    private Integer currentPage;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean hasNext;

    @Builder
    public ResponseReviewPageVo(List<ResponseReviewDto> content, Integer currentPage, Integer pageSize, Long totalElements, Integer totalPages, boolean hasNext) {
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.hasNext = hasNext;
    }

    public static ResponseReviewPageVo from(Page<ResponseReviewDto> page) {
        return ResponseReviewPageVo.builder()
                .content(page.getContent())
                .currentPage(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .hasNext(page.hasNext())
                .build();
    }

}
