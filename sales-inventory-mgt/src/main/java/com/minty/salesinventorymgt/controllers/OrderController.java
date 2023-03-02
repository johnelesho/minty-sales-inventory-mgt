package com.minty.salesinventorymgt.controllers;


import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.response.ApiResponse;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.models.Order;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController implements AppController<OrderRequest> {

    final AppService<Order, OrderRequest, OrderResponse> orderService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse> createNew(@RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.addNew(request);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .data(orderResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<OrderResponse> orderResponse = orderService.findAll(pr);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(orderResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{order-number}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable("order-number") String orderNumber) {
        OrderResponse orderResponse = orderService.findOne(orderNumber);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(orderResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<ApiResponse> updateOne(String code, OrderRequest request) {
        OrderResponse orderResponse = orderService.updateOne(code, request);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(orderResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }


}
