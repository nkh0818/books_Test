package com.autotour.domain.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autotour.domain.entity.PlaceMaster;
import com.autotour.domain.repository.PlaceMasterRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceMasterRepository placeMasterRepository;

    // 이름으로 관광지 검색 (예: /api/places/search?query=경복궁)
    @GetMapping("/search")
    public ResponseEntity<List<PlaceMaster>> searchPlaces(@RequestParam String query) {
        return ResponseEntity.ok(placeMasterRepository.findByTitleContaining(query));
    }

    // 특정 지역/테마별 목록 보기
    @GetMapping("/filter")
    public ResponseEntity<List<PlaceMaster>> filterPlaces(
            @RequestParam String areaCode,
            @RequestParam String contentTypeId) {
        return ResponseEntity.ok(placeMasterRepository.findByAreaCodeAndContentTypeId(areaCode, contentTypeId));
    }
}