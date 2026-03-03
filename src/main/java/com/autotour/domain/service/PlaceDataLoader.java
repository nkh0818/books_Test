package com.autotour.domain.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.autotour.domain.entity.PlaceMaster;
import com.autotour.domain.repository.PlaceMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PlaceDataLoader implements CommandLineRunner {
    private final PlaceMasterRepository placeMasterRepository;
    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;

    @Override
    public void run(String... args) throws Exception {
        if (placeMasterRepository.count() == 0) {
            Resource resource = resourceLoader.getResource("classpath:places.json");
            if (resource.exists()) {
                // 대소문자 구분 없이 매핑하도록 설정 강화
                objectMapper.configure(com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
                        true);

                List<PlaceMaster> places = objectMapper.readValue(
                        resource.getInputStream(),
                        new TypeReference<List<PlaceMaster>>() {
                        });

                if (!places.isEmpty() && places.get(0).getContentId() == null) {
                    System.out.println("❌ 에러: JSON의 첫 번째 항목을 읽었지만 ID(contentId)가 비어있습니다.");
                    System.out.println("참고용 JSON 첫 줄: " + places.get(0).toString());
                    return;
                }

                placeMasterRepository.saveAll(places);
                System.out.println("✅ JSON으로부터 " + places.size() + "개의 데이터를 DB에 로드했습니다.");
            }
        }
    }
}
