package com.starbucks.back.product.dto.in;

import com.starbucks.back.product.vo.in.RequestDeleteThumbnailVo;
import com.starbucks.back.product.vo.in.RequestThumbnailVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteThumbnailDto {

    private Long id;

    @Builder
    public RequestDeleteThumbnailDto(Long id) {
        this.id = id;
    }

    public static RequestDeleteThumbnailDto from (RequestDeleteThumbnailVo requestDeleteThumbnailVo) {
        return RequestDeleteThumbnailDto.builder()
                .id(requestDeleteThumbnailVo.getId())
                .build();
    }

}
