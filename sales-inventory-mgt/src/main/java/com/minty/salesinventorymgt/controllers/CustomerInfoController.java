package com.minty.salesinventorymgt.controllers;


import com.minty.lib.dtos.request.CustomerInfoRequest;
import com.minty.lib.dtos.response.ApiResponse;
import com.minty.lib.dtos.response.CustomerInfoResponse;
import com.minty.lib.models.CustomerInfo;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerInfoController implements AppController<CustomerInfoRequest> {
    final AppService<CustomerInfo, CustomerInfoRequest, CustomerInfoResponse> customerInfoService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse> createNew(@RequestBody CustomerInfoRequest productRequest) {
        CustomerInfoResponse customerInfoResponse = customerInfoService.addNew(productRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .data(customerInfoResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }


    @Override
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<CustomerInfoResponse> customerInfoResponses = customerInfoService.findAll(pr);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(customerInfoResponses)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable("code") String code) {
        CustomerInfoResponse customerInfoResponse = customerInfoService.findOne(code);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(customerInfoResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @Override
    @PutMapping("/{code}")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable("code") String code, @RequestBody CustomerInfoRequest request) {
        CustomerInfoResponse customerInfoResponse = customerInfoService.updateOne(code, request);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(customerInfoResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
