package com.starbucks.back.season.application;

import com.starbucks.back.season.dto.in.RequestAddSeasonListDto;
import com.starbucks.back.season.dto.in.RequestDeleteSeasonListDto;
import com.starbucks.back.season.dto.in.RequestUpdateSeasonListDto;
import com.starbucks.back.season.dto.out.ResponseSeasonListDto;

import java.util.List;

public interface SeasonListService {

    /**
     * 시즌 리스트 추가
     * @param requestAddSeasonListDto
     */
    void addSeasonList(RequestAddSeasonListDto requestAddSeasonListDto);

    /**
     * 시즌 리스트 id로 시즌 리스트 조회
     * @param id
     */
    ResponseSeasonListDto getSeasonListById(Long id);

    /**
     * 시즌 아이디로 시즌 리스트 조회
     * @param seasonId
     */
    List<ResponseSeasonListDto> getSeasonListBySeasonId(Long seasonId);

    /**
     * 상품 UUID로 시즌 리스트 조회
     * @param productUuid
     */
    List<ResponseSeasonListDto> getSeasonListByProductUuid(String productUuid);

    /**
     * 시즌 리스트 전체 조회
     */
    List<ResponseSeasonListDto> getAllSeasonLists();

    /**
     * 시즌 리스트 수정
     * @param requestUpdateSeasonListDto
     */
    void updateSeasonList(RequestUpdateSeasonListDto requestUpdateSeasonListDto);

    /**
     * 시즌 리스트 삭제
     * @param requestDeleteSeasonListDto
     */
    void deleteSeasonList(RequestDeleteSeasonListDto requestDeleteSeasonListDto);

}
