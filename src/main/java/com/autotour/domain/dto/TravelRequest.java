package com.autotour.domain.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TravelRequest {
    private String region; // 예: "서울", "부산"
    private LocalDate startDate; // 여행 시작일
    private String theme; // 예: "12"(관광지), "14"(문화시설), "39"(음식)
}