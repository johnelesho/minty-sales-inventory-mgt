package com.minty.reportservice.controllers;


import com.minty.lib.dtos.response.ApiResponse;
import com.minty.reportservice.dto.IOrderReport;
import com.minty.reportservice.services.ReportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/reports/orders")
@RequiredArgsConstructor
public class ReportController {

    final ReportServiceImpl reportService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam(value = "startDate", required = false) LocalDate startDate,
                                              @RequestParam(value = "endDate", required = false) LocalDate endDate) {
        List<IOrderReport> report;
        if (startDate == null || endDate == null) {
            report = reportService.getOrderReport();
        } else {
            report = reportService.getOrderReport(startDate, endDate);
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(report)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
