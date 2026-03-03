// package com.autotour.domain.controller;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.reactive.function.client.WebClient;
// import java.net.URI;
// import java.net.URLDecoder;
// import java.nio.charset.StandardCharsets;
// import org.springframework.web.util.UriComponentsBuilder;

// @RestController
// public class ApiDebugController {

// @Value("${api.tour.serviceKey}")
// private String tourKey;

// private final WebClient webClient = WebClient.builder().build();

// @GetMapping("/api/test/tour")
// public String testTourApi(@RequestParam(defaultValue = "1") String areaCode)
// {
// try {
// // 1. 키 디코딩 (가장 중요한 부분!)
// String decodedKey = URLDecoder.decode(tourKey, StandardCharsets.UTF_8);

// // 2. 수동으로 URL 조립 (관광공사 areaBasedList2)
// URI uri = UriComponentsBuilder
// .fromHttpUrl("http://apis.data.go.kr/B551011/KorService2/areaBasedList2")
// .queryParam("serviceKey", decodedKey)
// .queryParam("numOfRows", "10")
// .queryParam("pageNo", "1")
// .queryParam("MobileOS", "ETC")
// .queryParam("MobileApp", "TestApp")
// .queryParam("_type", "json")
// .queryParam("areaCode", areaCode) // 서울: 1
// .build(true)
// .toUri();

// System.out.println("테스트 호출 URL: " + uri);

// // 3. 호출 후 결과 그대로 리턴
// return webClient.get()
// .uri(uri)
// .retrieve()
// .bodyToMono(String.class)
// .block();
// } catch (Exception e) {
// return "에러 발생: " + e.getMessage();
// }
// }
// }