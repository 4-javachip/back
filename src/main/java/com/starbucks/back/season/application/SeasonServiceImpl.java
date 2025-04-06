package com.starbucks.back.season.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.season.domain.Season;
import com.starbucks.back.season.dto.in.RequestAddSeasonDto;
import com.starbucks.back.season.dto.in.RequestDeleteSeasonDto;
import com.starbucks.back.season.dto.in.RequestUpdateSeasonDto;
import com.starbucks.back.season.dto.out.ResponseSeasonDto;
import com.starbucks.back.season.infrastructure.SeasonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository seasonRepository;

    /**
     * 시즌 추가
     * @param requestAddSeasonDto
     */
    @Transactional
    @Override
    public void addSeason(RequestAddSeasonDto requestAddSeasonDto) {
        if(seasonRepository.existsByNameAndDeletedFalse(requestAddSeasonDto.getName())) {
            throw new BaseException(BaseResponseStatus.DUPLICATED_SEASON);
        }
        seasonRepository.save(requestAddSeasonDto.toEntity());
    }

    /**
     * id로 시즌 조회
     * @param id
     */
    @Override
    public ResponseSeasonDto getSeasonById(Long id) {
        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_SEASON));
        return ResponseSeasonDto.from(season);
    }

    /**
     * 시즌 이름으로 시즌 조회
     * @param name
     */
    @Override
    public ResponseSeasonDto getSeasonByName(String name) {
        Season season = seasonRepository.findByNameAndDeletedFalse(name)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_SEASON));
        return ResponseSeasonDto.from(season);
    }

    /**
     * 시즌 전체 조회
     */
    @Override
    public List<ResponseSeasonDto> getAllSeasons() {
        return seasonRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseSeasonDto::from)
                .toList();
    }

    /**
     * 시즌 수정
     * @param requestUpdateSeasonDto
     */
    @Transactional
    @Override
    public void updateSeason(RequestUpdateSeasonDto requestUpdateSeasonDto) {
        seasonRepository.save(requestUpdateSeasonDto.updateEntity());
    }

    /**
     * 시즌 삭제
     * @param requestDeleteSeasonDto
     */
    @Transactional
    @Override
    public void deleteSeason(RequestDeleteSeasonDto requestDeleteSeasonDto) {
        Season season = seasonRepository.findById(requestDeleteSeasonDto.getId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_SEASON));
        season.softDelete();
    }
}
