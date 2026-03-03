package com.autotour.domain.controller;

import com.autotour.domain.entity.Itinerary;
import com.autotour.domain.dto.CustomRouteRequest;
import com.autotour.domain.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tour")
@RequiredArgsConstructor
public class TourController {
    private final RecommendationService recommendationService;

    @PostMapping("/generate-custom")
    public ResponseEntity<Itinerary> generateCustomItinerary(@RequestBody CustomRouteRequest request) {
        // [로그인 체크 삭제됨] 누구나 저장 가능
        return ResponseEntity.ok(recommendationService.createCustomItinerary(request));
    }
}