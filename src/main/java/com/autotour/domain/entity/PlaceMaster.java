package com.autotour.domain.entity;

import com.fasterxml.jackson.annotation.JsonAlias; // 추가
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON에 필드가 더 많아도 에러 안 나게 함
public class PlaceMaster {

    @Id
    @JsonAlias({ "contentid", "contentId", "content_id" }) // 여러 후보 이름을 다 허용
    private String contentId;

    @JsonAlias({ "title", "PLACE_NAME" })
    private String title;

    @JsonAlias({ "addr", "addr1", "address" })
    private String addr;

    @JsonAlias({ "mapx", "mapX" })
    private String mapX;

    @JsonAlias({ "mapy", "mapY" })
    private String mapY;

    @JsonAlias({ "firstimage", "firstImage" })
    private String firstImage;

    @JsonAlias({ "contenttypeid", "contentTypeId" })
    private String contentTypeId;

    @JsonAlias({ "areacode", "areaCode" })
    private String areaCode;
}