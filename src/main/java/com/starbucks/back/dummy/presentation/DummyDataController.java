package com.starbucks.back.dummy.presentation;

import com.starbucks.back.dummy.application.DummyDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dummy")
@RequiredArgsConstructor
public class DummyDataController {

    private final DummyDataService dummyDataService;

    /**
     * 더미 데이터 삽입
     */
    @PostMapping
    public ResponseEntity<String> insertDummyData() {
        String result = dummyDataService.generateDummyProducts();
        return ResponseEntity.ok(result);
    }

    /**
     * 더미 데이터 삭제 (상품명 '더미 상품%' 기준 전체 삭제)
     */
    @DeleteMapping
    public ResponseEntity<String> deleteDummyData() {
        String result = dummyDataService.deleteAllDummyProducts();
        return ResponseEntity.ok(result);
    }
}
