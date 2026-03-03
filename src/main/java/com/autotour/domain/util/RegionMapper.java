package com.autotour.domain.util;

import java.util.HashMap;
import java.util.Map;

public class RegionMapper {

    private static final Map<String, String> WEATHER_REG_ID = new HashMap<>();
    private static final Map<String, String> TOUR_AREA_CODE = new HashMap<>();

    static {
        // 기상청 중기예보 구역 코드 (제공해주신 데이터 기반)
        WEATHER_REG_ID.put("서울", "11B00000"); // 서울.인천.경기
        WEATHER_REG_ID.put("인천", "11B00000");
        WEATHER_REG_ID.put("경기도", "11B00000");
        WEATHER_REG_ID.put("강원도", "11D10000"); // 영서 기준
        WEATHER_REG_ID.put("대전", "11C20000"); // 충남권
        WEATHER_REG_ID.put("부산", "11H20000"); // 경남권
        WEATHER_REG_ID.put("제주", "11G00000");
        // ... 필요한 만큼 추가

        // 관광공사 지역 코드 (areaCode)
        TOUR_AREA_CODE.put("서울", "1");
        TOUR_AREA_CODE.put("인천", "2");
        TOUR_AREA_CODE.put("대전", "3");
        TOUR_AREA_CODE.put("대구", "4");
        TOUR_AREA_CODE.put("광주", "5");
        TOUR_AREA_CODE.put("부산", "6");
        TOUR_AREA_CODE.put("울산", "7");
        TOUR_AREA_CODE.put("세종", "8");
        TOUR_AREA_CODE.put("경기도", "31");
        TOUR_AREA_CODE.put("강원도", "32");
        TOUR_AREA_CODE.put("제주", "39");
    }

    public static String getTourAreaCode(String regionName) {
        // regionName이 "서울 " 처럼 공백이 포함될 경우를 대비해 trim() 추가
        return TOUR_AREA_CODE.getOrDefault(regionName.trim(), "1");
    }

    public static String getWeatherRegId(String regionName) {
        return WEATHER_REG_ID.getOrDefault(regionName.trim(), "11B00000");
    }
}