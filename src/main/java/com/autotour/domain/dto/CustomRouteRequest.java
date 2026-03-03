package com.autotour.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CustomRouteRequest {
    private String title;
    private String region;
    private LocalDate travelDate;
    private String memo;
    private List<PlaceItemRequest> selectedPlaces; // 장소 리스트

    @Data
    public static class PlaceItemRequest {
        private String title;
        private String mapX;
        private String mapY;
    }
}