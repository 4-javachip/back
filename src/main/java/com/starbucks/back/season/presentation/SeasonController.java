package com.starbucks.back.season.presentation;

import com.starbucks.back.common.entity.BaseResponseEntity;
import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.season.application.SeasonService;
import com.starbucks.back.season.dto.in.RequestAddSeasonDto;
import com.starbucks.back.season.dto.in.RequestDeleteSeasonDto;
import com.starbucks.back.season.dto.in.RequestUpdateSeasonDto;
import com.starbucks.back.season.dto.out.ResponseSeasonDto;
import com.starbucks.back.season.vo.in.RequestAddSeasonVo;
import com.starbucks.back.season.vo.in.RequestDeleteSeasonVo;
import com.starbucks.back.season.vo.in.RequestUpdateSeasonVo;
import com.starbucks.back.season.vo.out.ResponseSeasonVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/season")
@RestController
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    /**
     * 시즌 추가
     * @param requestAddSeasonVo
     */
    @Operation(summary = "시즌 추가 API", description = "시즌 추가 API 입니다.", tags = {"Season-Service"})
    @PostMapping
    public BaseResponseEntity<Void> addSeason(@RequestBody RequestAddSeasonVo requestAddSeasonVo) {
        seasonService.addSeason(RequestAddSeasonDto.from(requestAddSeasonVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 시즌 id로 시즌 조회
     * @param id
     */
    @Operation(summary = "id로 시즌 조회 API", description = "id로 시즌 조회 API 입니다.", tags = {"Season-Service"})
    @GetMapping("/{id}")
    public BaseResponseEntity<ResponseSeasonVo> getSeasonById(@PathVariable Long id) {
        ResponseSeasonDto responseSeasonDto = seasonService.getSeasonById(id);
        return new BaseResponseEntity<>(responseSeasonDto.toVo());
    }

    /**
     * 시즌 이름으로 시즌 조회
     * @param name
     */
    @Operation(summary = "이름으로 시즌 조회 API", description = "이름으로 시즌 조회 API 입니다.", tags = {"Season-Service"})
    @GetMapping("/search")
    public BaseResponseEntity<ResponseSeasonVo> getSeasonByName(@RequestParam String name) {
        ResponseSeasonDto responseSeasonDto = seasonService.getSeasonByName(name);
        return new BaseResponseEntity<>(responseSeasonDto.toVo());
    }

    /**
     * 시즌 전체 조회
     */
    @Operation(summary = "시즌 전체 조회 API", description = "시즌 전체 조회 API 입니다.", tags = {"Season-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseSeasonVo>> getAllSeasons() {
        List<ResponseSeasonVo> result = seasonService.getAllSeasons()
                .stream()
                .map(ResponseSeasonDto::toVo)
                .toList();
        return new BaseResponseEntity<>(result);
    }

    /**
     * 시즌 수정
     * @param requestUpdateSeasonVo
     */
    @Operation(summary = "시즌 수정 API", description = "시즌 수정 API 입니다.", tags = {"Season-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateSeason(@RequestBody RequestUpdateSeasonVo requestUpdateSeasonVo) {
        seasonService.updateSeason(RequestUpdateSeasonDto.from(requestUpdateSeasonVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 시즌 삭제
     * @param requestDeleteSeasonVo
     */
    @Operation(summary = "시즌 삭제 API", description = "시즌 삭제 API 입니다.", tags = {"Season-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteSeason(@RequestBody RequestDeleteSeasonVo requestDeleteSeasonVo) {
        seasonService.deleteSeason(RequestDeleteSeasonDto.of(requestDeleteSeasonVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
