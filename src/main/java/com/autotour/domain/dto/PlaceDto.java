package com.autotour.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private String title;
    private String mapX; // 경도
    private String mapY; // 위도
    private String contentId;
    private double score; // 추천 점수
    private double distance; // 이전 장소와의 거리 (계산용)
}