package com.starbucks.back.shippingaddress.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.util.SecurityUtil;
import com.starbucks.back.shippingaddress.application.UserShippingAddressService;
import com.starbucks.back.shippingaddress.dto.in.RequestDeleteShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.in.RequestShippingAddressAndUserDto;
import com.starbucks.back.shippingaddress.dto.in.RequestUpdateShippingAddressDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadShippingAddressWithDefaultedDto;
import com.starbucks.back.shippingaddress.dto.out.ResponseReadUserShippingAddressDto;
import com.starbucks.back.shippingaddress.vo.in.RequestDeleteShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.in.RequestShippingAddressAndUserVo;
import com.starbucks.back.shippingaddress.vo.in.RequestUpdateShippingAddressVo;
import com.starbucks.back.shippingaddress.vo.out.ResponseReadShippingAddressListVo;
import com.starbucks.back.shippingaddress.vo.out.ResponseShippingAddressVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipping-address")
@RequiredArgsConstructor
public class UserShippingAddressController {

    private final UserShippingAddressService userShippingAddressService;
    private final SecurityUtil securityUtil;

    /**
     * 배송지 List 조회 by userUuid
     * @return
     */
    @Operation(summary = "배송지 리스트 조회 API", description = "배송지 List 조회 API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseReadShippingAddressListVo>> getUserShippingAddressAllListByUserUuid() {
        String userUuid = securityUtil.getCurrentUserUuid();

        List<ResponseReadShippingAddressListVo> result = userShippingAddressService
                .getUserShippingAddressAllListByUserUuid(userUuid)
                .stream()
                .map(ResponseReadUserShippingAddressDto::toVo)
                .toList();

        return new BaseResponseEntity<>(result);
    }

    /**
     * 기본배송지 객체 조회 by userUuid
     * @return
     */
    @Operation(summary = "배송지 객체 조회 API", description = "기본배송지 객체 조회 API 입니다.", tags = {"ShippingAddress-Service"})
    @GetMapping("/user/default")
    public BaseResponseEntity<ResponseShippingAddressVo> getUserDefaultShippingAddress() {
        String userUuid = securityUtil.getCurrentUserUuid();
        ResponseReadShippingAddressWithDefaultedDto responseReadShippingAddressWithDefaultedDto = userShippingAddressService
                .getDefaultShippingAddressByUserUuid(userUuid);

        return new BaseResponseEntity<>(responseReadShippingAddressWithDefaultedDto.toVo());
    }

    /**
     * 배송지 삭제 by userUuid, shippingAddressUuid
     * @param
     */
    @Operation(summary = "배송지 삭제 API", description = "배송지 객체 삭제 API 입니다.", tags = {"ShippingAddress-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteShippingAddress(
            @RequestBody RequestDeleteShippingAddressVo requestDeleteShippingAddressVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        userShippingAddressService.deleteShippingAddress(
                RequestDeleteShippingAddressDto.from(userUuid, requestDeleteShippingAddressVo)
        );

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 전부 삭제 by userUuid
     */
    @Operation(summary = "배송지 전체 삭제 API", description = "배송지 전체 삭제 API 입니다.", tags = {"ShippingAddress-Service"})
    @DeleteMapping("/user")
    public BaseResponseEntity<Void> deleteAllShippingAddressByUserUuid() {
        String userUuid = securityUtil.getCurrentUserUuid();

        userShippingAddressService.deleteAllShippingAddressByUserUuid(userUuid);

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 수정 by shippingAddressUuid
     * @param requestUpdateShippingAddressVo
     */
    @Operation(summary = "배송지 수정 API", description = "배송지 객체 수정 API 입니다.", tags = {"ShippingAddress-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateShippingAddressByUuid(
            @RequestBody RequestUpdateShippingAddressVo requestUpdateShippingAddressVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        userShippingAddressService.updateShippingAddress(
                RequestUpdateShippingAddressDto.from(userUuid, requestUpdateShippingAddressVo));

        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 배송지 추가 by userUuid, requestShippingAddressAndUserVo
     * @param
     */
    @Operation(summary = "배송지 추가 API", description = "배송지 추가 API 입니다.", tags = {"ShippingAddress-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addShippingAddress(
            @RequestBody RequestShippingAddressAndUserVo requestShippingAddressAndUserVo
    ) {
        String userUuid = securityUtil.getCurrentUserUuid();

        RequestShippingAddressAndUserDto requestShippingAddressAndUserDto= RequestShippingAddressAndUserDto.from(
                userUuid,
                requestShippingAddressAndUserVo
        );
        userShippingAddressService.addUserShippingAddress(requestShippingAddressAndUserDto);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
