package com.autotour.domain.service;

import com.autotour.domain.dto.PlaceItemDto;
import com.autotour.domain.util.RegionMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate(); // 단순한 RestTemplate 생성

    @Value("${api.weather.url}")
    private String weatherUrl;
    @Value("${api.weather.serviceKey}")
    private String weatherKey;

    @Value("${api.tour.url}")
    private String tourUrl;
    @Value("${api.tour.serviceKey}")
    private String tourKey;

    public List<PlaceItemDto> getTouristSpots(String regionName, String theme) {
        String areaCode = RegionMapper.getTourAreaCode(regionName);
        try {
            // 1. 서비스키 디코딩
            String decodedKey = URLDecoder.decode(tourKey, StandardCharsets.UTF_8);

            // 2. URI 빌드 (문제가 되는 listYN 제거, arrange 변경)
            URI uri = UriComponentsBuilder.fromHttpUrl(tourUrl)
                    .queryParam("serviceKey", decodedKey)
                    .queryParam("numOfRows", "50")
                    .queryParam("pageNo", "1")
                    .queryParam("MobileOS", "ETC")
                    .queryParam("MobileApp", "AutoTour")
                    .queryParam("_type", "json")
                    // .queryParam("listYN", "Y") <-- 이 부분이 에러 원인일 수 있어 제거함
                    .queryParam("arrange", "A") // Q 대신 A(제목순) 사용
                    .queryParam("contentTypeId", theme)
                    .queryParam("areaCode", areaCode)
                    .build(true)
                    .toUri();

            log.info("수정된 호출 URL: {}", uri);

            // RestTemplate으로 호출
            String response = restTemplate.getForObject(uri, String.class);
            log.info("응답 결과: {}", response); // 이제 여기에 JSON이 찍혀야 함

            return parseTourJson(response);
        } catch (Exception e) {
            log.error("Tour API Error: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    public String getWeatherForecast(String regionName) {
        String regId = RegionMapper.getWeatherRegId(regionName);
        String tmFc = getTmFc();

        try {
            String decodedKey = URLDecoder.decode(weatherKey, StandardCharsets.UTF_8);
            URI uri = UriComponentsBuilder.fromHttpUrl(weatherUrl)
                    .queryParam("serviceKey", decodedKey)
                    .queryParam("pageNo", "1")
                    .queryParam("numOfRows", "10")
                    .queryParam("dataType", "JSON")
                    .queryParam("regId", regId)
                    .queryParam("tmFc", tmFc)
                    .build(true)
                    .toUri();

            log.info("Weather API 호출: {}", uri);
            String response = restTemplate.getForObject(uri, String.class);

            return parseWeatherJson(response);
        } catch (Exception e) {
            log.error("Weather API Error: {}", e.getMessage());
            return "정보없음";
        }
    }

    // --- 나머지 private 메서드(getTmFc, parseWeatherJson, parseTourJson)는 이전과 동일 ---
    private String getTmFc() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        if (hour < 6)
            return now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "1800";
        else if (hour < 18)
            return now.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "0600";
        else
            return now.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "1800";
    }

    private String parseWeatherJson(String json) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode item = root.path("response").path("body").path("items").path("item").get(0);
            return item.path("wf3Am").asText();
        } catch (Exception e) {
            return "맑음";
        }
    }

    private List<PlaceItemDto> parseTourJson(String json) {
        List<PlaceItemDto> list = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode items = root.path("response").path("body").path("items").path("item");
            if (items.isArray()) {
                for (JsonNode item : items) {
                    list.add(PlaceItemDto.builder()
                            .title(item.path("title").asText())
                            .addr1(item.path("addr1").asText())
                            .mapx(item.path("mapx").asText())
                            .mapy(item.path("mapy").asText())
                            .contentid(item.path("contentid").asText())
                            .contenttypeid(item.path("contenttypeid").asText())
                            .firstimage(item.path("firstimage").asText())
                            .build());
                }
            }
            log.info("파싱된 관광지 개수: {}", list.size());
        } catch (Exception e) {
            log.error("JSON 파싱 에러");
        }
        return list;
    }
}