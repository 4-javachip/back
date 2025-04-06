package com.starbucks.back.season.application;

import com.starbucks.back.season.dto.in.RequestAddSeasonDto;
import com.starbucks.back.season.dto.in.RequestDeleteSeasonDto;
import com.starbucks.back.season.dto.in.RequestUpdateSeasonDto;
import com.starbucks.back.season.dto.out.ResponseSeasonDto;

import java.util.List;

public interface SeasonService {

    /**
     * 시즌 추가
     * @param requestAddSeasonDto
     */
    void addSeason(RequestAddSeasonDto requestAddSeasonDto);

    /**
     * 시즌 id로 시즌 조회
     * @param id
     */
    ResponseSeasonDto getSeasonById(Long id);

    /**
     * 시즌 이름으로 시즌 조회
     * @param name
     */
    ResponseSeasonDto getSeasonByName(String name);

    /**
     * 시즌 전체 조회
     */
    List<ResponseSeasonDto> getAllSeasons();

    /**
     * 시즌 수정
     * @param requestUpdateSeasonDto
     */
    void updateSeason(RequestUpdateSeasonDto requestUpdateSeasonDto);

    /**
     * 시즌 삭제
     * @param requestDeleteSeasonDto
     */
    void deleteSeason(RequestDeleteSeasonDto requestDeleteSeasonDto);

}
