package com.minty.salesinventorymgt.controllers;


import com.minty.lib.dtos.request.OrderRequest;
import com.minty.lib.dtos.response.ApiResponse;
import com.minty.lib.dtos.response.OrderResponse;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.enums.OrderStatus;
import com.minty.lib.enums.ProductStatus;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.Order;
import com.minty.salesinventorymgt.repositories.OrderRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController implements AppController<OrderRequest> {

    final AppService<Order, OrderRequest, OrderResponse> orderService;

    final OrderRepository orderRepository;

    final HelpMapper mapper;

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
    @PostMapping("/bulk")
    public ResponseEntity<ApiResponse> createNew(@RequestBody List<OrderRequest> requests) {
        List<OrderResponse> orderResponse = new ArrayList<>();
        for (OrderRequest req: requests
             ) {
            orderResponse.add(orderService.addNew(req));
        }

        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .data(orderResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/query")
    public ResponseEntity<ApiResponse> getByQuery(@RequestParam(value = "status", defaultValue = "PENDING") OrderStatus status) {
        List<OrderResponse> responses = orderRepository.findAllByStatusOrderByCreatedDateDesc(status).stream().map(mapper::convertToOrderResonse).toList();

        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(responses)
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
