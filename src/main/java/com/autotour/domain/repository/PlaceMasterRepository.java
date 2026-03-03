package com.autotour.domain.repository;

import com.autotour.domain.entity.PlaceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaceMasterRepository extends JpaRepository<PlaceMaster, String> {
    // 지역별로 관광지 검색할 때 사용
    List<PlaceMaster> findByAreaCodeAndContentTypeId(String areaCode, String contentTypeId);

    // 이름으로 검색할 때 사용 (사용자 직접 추가 기능용)
    List<PlaceMaster> findByTitleContaining(String title);
}