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
        // DB를 확인해서 데이터가 없으면 JSON 로드
        if (placeMasterRepository.count() == 0) {
            // 스프링의 ResourceLoader를 통해 파일을 가져옵니다.
            Resource resource = resourceLoader.getResource("classpath:places.json");

            if (resource.exists()) {
                // JSON 파일을 읽어서 List<PlaceMaster>로 변환
                List<PlaceMaster> places = objectMapper.readValue(
                        resource.getInputStream(),
                        new TypeReference<List<PlaceMaster>>() {
                        });

                // DB에 저장
                placeMasterRepository.saveAll(places);
                System.out.println("✅ JSON으로부터 " + places.size() + "개의 데이터를 DB에 로드했습니다.");
            } else {
                System.out.println("⚠️ places.json 파일을 찾을 수 없습니다. 경로를 확인하세요.");
            }
        } else {
            System.out.println("ℹ️ DB에 이미 데이터가 존재하여 JSON 로드를 건너뜁니다.");
        }
    }
}