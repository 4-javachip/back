package com.starbucks.back.season.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.season.domain.SeasonList;
import com.starbucks.back.season.dto.in.RequestAddSeasonListDto;
import com.starbucks.back.season.dto.in.RequestDeleteSeasonListDto;
import com.starbucks.back.season.dto.in.RequestUpdateSeasonListDto;
import com.starbucks.back.season.dto.out.ResponseSeasonListDto;
import com.starbucks.back.season.infrastructure.SeasonListRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonListServiceImpl implements SeasonListService {

    private final SeasonListRepository seasonListRepository;

    /**
     * 시즌 리스트 추가
     * @param requestAddSeasonListDto
     */
    @Transactional
    @Override
    public void addSeasonList(RequestAddSeasonListDto requestAddSeasonListDto) {
        seasonListRepository.save(requestAddSeasonListDto.toEntity());
    }

    /**
     * 시즌 리스트 id로 시즌 리스트 조회
     * @param id
     */
    @Override
    public ResponseSeasonListDto getSeasonListById(Long id) {
        SeasonList seasonList = seasonListRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_SEASON_LIST));
        return ResponseSeasonListDto.from(seasonList);
    }

    /**
     * 시즌 아이디로 시즌 리스트 조회
     * @param seasonId
     */
    @Override
    public List<ResponseSeasonListDto> getSeasonListBySeasonId(Long seasonId) {
        return seasonListRepository.findBySeasonIdAndDeletedFalse(seasonId)
                .stream()
                .map(ResponseSeasonListDto::from)
                .toList();
    }

    /**
     * 상품 UUID로 시즌 리스트 조회
     * @param productUuid
     */
    @Override
    public List<ResponseSeasonListDto> getSeasonListByProductUuid(String productUuid) {
        return seasonListRepository.findByProductUuidAndDeletedFalse(productUuid)
                .stream()
                .map(ResponseSeasonListDto::from)
                .toList();
    }

    /**
     * 시즌 리스트 전체 조회
     */
    @Override
    public List<ResponseSeasonListDto> getAllSeasonLists() {
        return seasonListRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseSeasonListDto::from)
                .toList();
    }

    /**
     * 시즌 리스트 수정
     * @param requestUpdateSeasonListDto
     */
    @Transactional
    @Override
    public void updateSeasonList(RequestUpdateSeasonListDto requestUpdateSeasonListDto) {
        seasonListRepository.save(requestUpdateSeasonListDto.updateEntity());
    }

    /**
     * 시즌 리스트 삭제
     * @param requestDeleteSeasonListDto
     */
    @Transactional
    @Override
    public void deleteSeasonList(RequestDeleteSeasonListDto requestDeleteSeasonListDto) {
        SeasonList seasonList = seasonListRepository.findById(requestDeleteSeasonListDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_SEASON_LIST));
        seasonList.softDelete();
    }
}
