package com.autotour.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sequence; // 방문 순서
    private String placeName; // 장소명
    private String mapX; // 경도
    private String mapY; // 위도
    private String imageUrl; // 이미지

    // [추가] 장소 타입: TOUR(관광지), STAY(숙소), CUSTOM(사용자 지정)
    private String placeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itinerary_id")
    @JsonIgnore
    private Itinerary itinerary;
}