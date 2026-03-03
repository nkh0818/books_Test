package com.autotour.domain.service;

import com.autotour.domain.entity.PlaceMaster;
import com.autotour.domain.repository.PlaceMasterRepository;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceBulkService {

    private final PlaceMasterRepository placeMasterRepository;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.tour.url}")
    private String tourUrl;
    @Value("${api.tour.serviceKey}")
    private String tourKey;

    /**
     * @param areaCode : 지역코드 (null 이거나 공백이면 전국 데이터 조회)
     */
    public String bulkLoad(String areaCode) {
        int pageNo = 1;
        int totalSaved = 0;
        String decodedKey = URLDecoder.decode(tourKey, StandardCharsets.UTF_8);

        log.info("관광지 대량 저장 시작... (대상 지역: {})", (areaCode == null || areaCode.isEmpty()) ? "전국" : areaCode);

        try {
            while (true) {
                // URI 빌드
                UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(tourUrl)
                        .queryParam("serviceKey", decodedKey)
                        .queryParam("numOfRows", "1000") // 최대치로 호출 횟수 절약
                        .queryParam("pageNo", pageNo)
                        .queryParam("MobileOS", "ETC")
                        .queryParam("MobileApp", "AutoTourBulk")
                        .queryParam("_type", "json");

                // areaCode가 있을 때만 파라미터 추가
                if (areaCode != null && !areaCode.isEmpty()) {
                    uriBuilder.queryParam("areaCode", areaCode);
                }

                URI uri = uriBuilder.build(true).toUri();
                String response = restTemplate.getForObject(uri, String.class);

                // JSON 파싱
                JsonNode root = objectMapper.readTree(response);
                JsonNode body = root.path("response").path("body");
                JsonNode items = body.path("items").path("item");

                // 데이터가 더 없으면 중단
                if (!items.isArray() || items.size() == 0) {
                    log.info("더 이상 가져올 데이터가 없습니다. 종료합니다.");
                    break;
                }

                List<PlaceMaster> bulkList = new ArrayList<>();
                for (JsonNode item : items) {
                    bulkList.add(PlaceMaster.builder()
                            .contentId(item.path("contentid").asText())
                            .title(item.path("title").asText())
                            .addr(item.path("addr1").asText())
                            .mapX(item.path("mapx").asText())
                            .mapY(item.path("mapy").asText())
                            .firstImage(item.path("firstimage").asText())
                            .contentTypeId(item.path("contenttypeid").asText())
                            .areaCode(item.path("areacode").asText()) // API가 주는 지역코드 저장
                            .build());
                }

                // 중복 키(contentId) 발생 시 업데이트 처리를 위해 saveAll 사용
                placeMasterRepository.saveAll(bulkList);
                totalSaved += bulkList.size();

                log.info("{} 페이지 완료 (현재까지 누적 저장: {}개)", pageNo, totalSaved);

                // API 서버 보호를 위해 약간의 지연 시간 추가
                Thread.sleep(200);
                pageNo++;

                // 안전장치: 너무 많이 호출되지 않도록 (전국 데이터는 보통 40~50페이지면 끝남)
                if (pageNo > 100)
                    break;
            }
            return "총 " + totalSaved + "개의 데이터를 우리 DB로 성공적으로 복사했습니다.";
        } catch (Exception e) {
            log.error("전국 데이터 저장 중 오류 발생", e);
            return "오류 발생: " + e.getMessage();
        }
    }
}