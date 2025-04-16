package com.starbucks.back.cart.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestUpdateAllCartCheckedDto {

    private String userUuid;
    private Boolean checked;

    @Builder
    public RequestUpdateAllCartCheckedDto(String userUuid, Boolean checked) {
        this.userUuid = userUuid;
        this.checked = checked;
    }

    // vo => dto
    public static RequestUpdateAllCartCheckedDto from(String userUuid, Boolean checked) {
        return RequestUpdateAllCartCheckedDto.builder()
                .userUuid(userUuid)
                .checked(checked)
                .build();
    }

}
