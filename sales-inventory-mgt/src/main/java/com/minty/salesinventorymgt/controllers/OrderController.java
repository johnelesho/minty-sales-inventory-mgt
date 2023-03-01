package com.minty.salesinventorymgt.controllers;

import com.minty.salesinventorymgt.dtos.request.OrderRequest;
import com.minty.salesinventorymgt.dtos.response.OrderResponse;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.models.Order;
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
public class OrderController implements AppController<OrderRequest, OrderResponse> {

    final AppService<Order, OrderRequest, OrderResponse> orderService;
    @Override
@PostMapping
    public ResponseEntity<OrderResponse> createNew(@RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.addNew(request);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size){
        PageRequest pr = PageRequest.of(page, size, Sort.by( Sort.Direction.DESC,"createdDate"));
        List<OrderResponse> orderResponse = orderService.findAll(pr);
        return new ResponseEntity<List<OrderResponse>>(orderResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{order-number}")
    public ResponseEntity<OrderResponse> getOne(@PathVariable("order-number") String orderNumber) {
        OrderResponse orderResponse = orderService.findOne(orderNumber);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }

    @Override
    @PutMapping
    public ResponseEntity<OrderResponse> updateOne(String code, OrderRequest request) {
        OrderResponse orderResponse = orderService.updateOne(code, request);
        return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
    }


}
