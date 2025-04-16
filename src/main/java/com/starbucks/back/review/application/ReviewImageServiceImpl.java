package com.starbucks.back.review.application;

import com.starbucks.back.common.entity.BaseResponseStatus;
import com.starbucks.back.common.exception.BaseException;
import com.starbucks.back.common.util.S3UploaderUtil;
import com.starbucks.back.review.domain.ReviewImage;
import com.starbucks.back.review.dto.in.RequestAddReviewImageDto;
import com.starbucks.back.review.dto.out.ResponseReviewImageDto;
import com.starbucks.back.review.infrastructure.ReviewImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService {

    private final ReviewImageRepository reviewImageRepository;
    private final S3UploaderUtil s3UploaderUtil;

    /**
     * 리뷰 이미지 등록 (S3에 업로드 후 저장)
     * @param requestAddReviewImageDto
     */
    @Transactional
    @Override
    public void addReviewImages(List<RequestAddReviewImageDto> requestAddReviewImageDto) {
        List<ReviewImage> result = new ArrayList<>();

        for (RequestAddReviewImageDto dto : requestAddReviewImageDto) {
            MultipartFile file = dto.getImage(); // 이미지 파일 꺼내기
            String imageUrl = s3UploaderUtil.upload(file, "review"); // S3에 업로드
            ReviewImage reviewImage = dto.toEntity(imageUrl); // ReviewImage 엔티티로 변환
            result.add(reviewImage); // 리스트에 추가
        }
        reviewImageRepository.saveAll(result);
    }

    /**
     * reviewUuid로 리뷰 이미지 조회
     * @param reviewUuid
     */
    @Override
    public List<ResponseReviewImageDto> getReviewImagesByReviewUuid(String reviewUuid) {
        return reviewImageRepository.findAllByReviewUuidAndDeletedFalse(reviewUuid)
                .stream()
                .map(ResponseReviewImageDto::from)
                .toList();
    }

    /**
     * 리뷰 이미지 삭제
     * @param reviewUuid
     */
    @Transactional
    @Override
    public void deleteReviewImagesByReviewUuid(String reviewUuid) {
        List<ReviewImage> images = reviewImageRepository.findAllByReviewUuidAndDeletedFalse(reviewUuid);

        if (images.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_REVIEW_PHOTO);
        }

        images.forEach(ReviewImage::softDelete);
    }
}
