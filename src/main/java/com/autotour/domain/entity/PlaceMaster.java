package com.autotour.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceMaster {
    @Id
    private String contentId; // 관광공사 고유 ID (기본키로 사용)

    private String title;
    private String addr;
    private String mapX;
    private String mapY;
    private String firstImage;
    private String contentTypeId; //
    private String areaCode; // 지역 코드
}