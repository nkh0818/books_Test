package com.autotour.domain.service;

import com.autotour.domain.dto.CustomRouteRequest;
import com.autotour.domain.entity.Itinerary;
import com.autotour.domain.entity.ItineraryItem;
import com.autotour.domain.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final ItineraryRepository itineraryRepository;
    private final ExternalApiService apiService;

    /**
     * [신규 생성] 사용자가 편집한 경로를 그대로 저장
     */
    @Transactional
    public Itinerary createCustomItinerary(CustomRouteRequest request) {
        // 1. 날씨 정보 가져오기 (메모에 추가하지 않고 데이터만 저장)
        String weather = apiService.getWeatherForecast(request.getRegion());

        // 2. 여행 일정 마스터 생성
        Itinerary itinerary = Itinerary.builder()
                .title(request.getTitle())
                .region(request.getRegion())
                .travelDate(request.getTravelDate())
                .weatherCondition(weather)
                .memo(request.getMemo()) // [수정] AI 안내 문구 삭제, 사용자 메모만 저장
                .items(new ArrayList<>())
                .build();

        // 3. 상세 장소 아이템들 추가 (프론트에서 보낸 순서 그대로 저장)
        if (request.getSelectedPlaces() != null) {
            for (int i = 0; i < request.getSelectedPlaces().size(); i++) {
                CustomRouteRequest.PlaceItemRequest p = request.getSelectedPlaces().get(i);

                itinerary.addItem(ItineraryItem.builder()
                        .sequence(i + 1)
                        .placeName(p.getTitle()) // 사용자가 클릭/검색한 이름 그대로 저장
                        .mapX(p.getMapX())
                        .mapY(p.getMapY())
                        .placeType("TOUR")
                        .build());
            }
        }

        return itineraryRepository.save(itinerary);
    }

    /**
     * [수정] 기존 일정을 사용자가 편집한 내용으로 업데이트
     */
    @Transactional
    public Itinerary updateCustomItinerary(Long id, CustomRouteRequest request) {
        Itinerary itinerary = itineraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("수정할 일정을 찾을 수 없습니다."));

        // 1. 기본 정보 업데이트
        itinerary.setTitle(request.getTitle());
        itinerary.setTravelDate(request.getTravelDate());
        itinerary.setMemo(request.getMemo());

        // 2. 기존 경로 아이템 삭제 (orphanRemoval=true 설정으로 인해 리스트 비우면 DB 삭제됨)
        itinerary.getItems().clear();
        itineraryRepository.saveAndFlush(itinerary); // 즉시 반영

        // 3. 수정된 장소들 순서대로 다시 추가
        if (request.getSelectedPlaces() != null) {
            for (int i = 0; i < request.getSelectedPlaces().size(); i++) {
                CustomRouteRequest.PlaceItemRequest p = request.getSelectedPlaces().get(i);

                itinerary.addItem(ItineraryItem.builder()
                        .sequence(i + 1)
                        .placeName(p.getTitle())
                        .mapX(p.getMapX())
                        .mapY(p.getMapY())
                        .placeType("TOUR")
                        .build());
            }
        }

        return itineraryRepository.save(itinerary);
    }
}