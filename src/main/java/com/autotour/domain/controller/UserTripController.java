package com.autotour.domain.controller;

import com.autotour.domain.entity.Itinerary;
import com.autotour.domain.dto.CustomRouteRequest;
import com.autotour.domain.service.RecommendationService;
import com.autotour.domain.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-trips")
@RequiredArgsConstructor
public class UserTripController {

    private final ItineraryRepository itineraryRepository;
    private final RecommendationService recommendationService;

    // 1. 저장된 모든 여행 목록 가져오기 (로그인 체크 삭제)
    @GetMapping
    public ResponseEntity<List<Itinerary>> getMyTrips() {
        // [수정] findAll() 대신 위에서 만든 findAllWithItems()를 호출합니다.
        return ResponseEntity.ok(itineraryRepository.findAllWithItems());
    }

    // 2. 여행 수정하기 (로그인 체크 삭제)
    @PutMapping("/{id}")
    public ResponseEntity<Itinerary> updateTrip(
            @PathVariable Long id,
            @RequestBody CustomRouteRequest request) {

        // 서비스에서도 User 파라미터를 지웠으므로 여기서도 id와 request만 넘깁니다.
        Itinerary updated = recommendationService.updateCustomItinerary(id, request);
        return ResponseEntity.ok(updated);
    }

    // 3. 여행 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        itineraryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}