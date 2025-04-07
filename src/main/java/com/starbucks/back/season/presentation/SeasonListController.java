package com.starbucks.back.season.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.season.application.SeasonListService;
import com.starbucks.back.season.dto.in.RequestAddSeasonListDto;
import com.starbucks.back.season.dto.in.RequestDeleteSeasonListDto;
import com.starbucks.back.season.dto.in.RequestUpdateSeasonListDto;
import com.starbucks.back.season.dto.out.ResponseSeasonListDto;
import com.starbucks.back.season.vo.in.RequestAddSeasonListVo;
import com.starbucks.back.season.vo.in.RequestDeleteSeasonListVo;
import com.starbucks.back.season.vo.in.RequestUpdateSeasonListVo;
import com.starbucks.back.season.vo.out.ResponseSeasonListVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/season-list")
@RestController
@RequiredArgsConstructor
public class SeasonListController {

    private final SeasonListService seasonListService;

    /**
     * 시즌 리스트 추가
     * @param requestAddSeasonListVo
     */
    @Operation(summary = "시즌 리스트 추가 API", description = "시즌 리스트 추가 API 입니다.", tags = {"Season-List-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addSeasonList(@RequestBody RequestAddSeasonListVo requestAddSeasonListVo) {
        seasonListService.addSeasonList(RequestAddSeasonListDto.of(requestAddSeasonListVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 시즌 리스트 id로 시즌 리스트 조회
     * @param id
     */
    @Operation(summary = "시즌 리스트 id로 시즌 리스트 조회 API", description = "시즌 리스트 id로 시즌 리스트 조회 API 입니다.", tags = {"Season-List-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseSeasonListVo> getSeasonListById(@PathVariable("id") Long id) {
        ResponseSeasonListDto responseSeasonListDto = seasonListService.getSeasonListById(id);
        return new BaseResponseEntity<>(responseSeasonListDto.toVo());
    }

    /**
     * 시즌 id로 시즌 리스트 조회
     * @param seasonId
     */
    @Operation(summary = "시즌 id로 시즌 리스트 조회 API", description = "시즌 id로 시즌 리스트 조회 API 입니다.", tags = {"Season-List-Service"})
    @GetMapping("/list/{seasonId}")
    public BaseResponseEntity<List<ResponseSeasonListVo>> getSeasonListBySeasonId(@PathVariable("seasonId") Long seasonId) {
        List<ResponseSeasonListVo> result = seasonListService.getSeasonListBySeasonId(seasonId)
                .stream()
                .map(ResponseSeasonListDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 상품 UUID로 시즌 리스트 조회
     * @param productUuid
     */
    @Operation(summary = "상품 UUID로 시즌 리스트 조회 API", description = "상품 UUID로 시즌 리스트 조회 API 입니다.", tags = {"Season-List-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<List<ResponseSeasonListVo>> getSeasonListByProductUuid(@RequestParam("productUuid") String productUuid) {
        List<ResponseSeasonListVo> result = seasonListService.getSeasonListByProductUuid(productUuid)
                .stream()
                .map(ResponseSeasonListDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 시즌 리스트 전체 조회
     */
    @Operation(summary = "시즌 리스트 전체 조회 API", description = "시즌 리스트 전체 조회 API 입니다.", tags = {"Season-List-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseSeasonListVo>> getAllSeasonLists() {
        List<ResponseSeasonListVo> result = seasonListService.getAllSeasonLists()
                .stream()
                .map(ResponseSeasonListDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 시즌 리스트 수정
     * @param requestUpdateSeasonListVo
     */
    @Operation(summary = "시즌 리스트 수정 API", description = "시즌 리스트 수정 API 입니다.", tags = {"Season-List-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateSeasonList(@RequestBody RequestUpdateSeasonListVo requestUpdateSeasonListVo) {
        seasonListService.updateSeasonList(RequestUpdateSeasonListDto.from(requestUpdateSeasonListVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 시즌 리스트 삭제
     * @param requestDeleteSeasonListVo
     */
    @Operation(summary = "시즌 리스트 삭제 API", description = "시즌 리스트 삭제 API 입니다.", tags = {"Season-List-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteSeasonList(@RequestBody RequestDeleteSeasonListVo requestDeleteSeasonListVo) {
        seasonListService.deleteSeasonList(RequestDeleteSeasonListDto.of(requestDeleteSeasonListVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
