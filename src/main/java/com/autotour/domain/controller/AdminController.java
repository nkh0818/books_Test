package com.autotour.domain.controller;

import com.autotour.domain.service.PlaceBulkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PlaceBulkService placeBulkService;

    // 1. 특정 지역만: http://localhost:8080/api/admin/bulk?areaCode=1
    // 2. 전국 데이터: http://localhost:8080/api/admin/bulk
    @GetMapping("/bulk")
    public String loadData(@RequestParam(required = false) String areaCode) {
        return placeBulkService.bulkLoad(areaCode);
    }
}