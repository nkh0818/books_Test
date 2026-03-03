package com.autotour.domain.service;

import com.autotour.domain.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourApiService {

    @Value("${api.tour.serviceKey}")
    private String tourApiKey;

    @Value("${api.tour.url}")
    private String tourApiUrl;

    // WebClient를 사용한 비동기/동기 호출 (여기서는 간단히 로직만 설명)
    public List<PlaceDto> fetchTouristSpots(String region, String theme) {
        // 실제로는 WebClient나 RestTemplate을 사용하여
        // tourApiUrl + /areaBasedList1 등을 호출하고 JSON 파싱해야 함.
        // 현재는 더미 데이터를 반환하여 로직 흐름을 완성합니다.

        List<PlaceDto> places = new ArrayList<>();

        // Mock Data (실제 구현 시 API 결과 파싱해서 넣기)
        places.add(PlaceDto.builder().title("경복궁").mapX("126.976").mapY("37.579").contentId("1").score(0).build());
        places.add(PlaceDto.builder().title("남산타워").mapX("126.988").mapY("37.551").contentId("2").score(0).build());
        places.add(PlaceDto.builder().title("롯데월드").mapX("127.099").mapY("37.511").contentId("3").score(0).build());
        places.add(PlaceDto.builder().title("한강공원").mapX("126.934").mapY("37.528").contentId("4").score(0).build());

        return places;
    }

    public String fetchWeather(String region, String date) {
        // 기상청 API 호출 로직
        // 날씨가 맑음이면 "Clear", 비오면 "Rain" 반환 등
        return "Clear";
    }
}