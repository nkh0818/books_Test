package com.autotour.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceItemDto {
    private String title;
    private String addr1;
    private String mapx;
    private String mapy;
    private String contentid;
    private String contenttypeid;
    private String firstimage; // 대표 이미지

    // 내부 로직용 필드
    private double score; // 추천 점수
    private double distance; // 거리 계산용
}