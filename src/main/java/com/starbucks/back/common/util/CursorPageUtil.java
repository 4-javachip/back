package com.starbucks.back.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CursorPageUtil<T, C> {

    private List<T> content;
    private C nextCursor; // 제네릭 커서 타입
    private Boolean hasNext;
    private Integer pageSize;

    public boolean hasNext() {
        return nextCursor != null;
    }

    @Builder
    public CursorPageUtil(
            List<T> content, // 현재 페이지 데이터 목록
            C nextCursor, // 다음 페이지 조회용 커서
            Boolean hasNext, // 다음 페이지 존재 여부
            Integer pageSize // 한 페이지당 데이터 개수
    ) {
        this.content = content;
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
        this.pageSize = pageSize;
    }

}
