package com.minty.salesinventorymgt.controllers;


import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.ApiResponse;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.models.Product;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@Validated
public class ProductController implements AppController<ProductRequest> {

    final AppService<Product, ProductRequest, ProductResponse> productService;

    @Override
    @PostMapping
    public ResponseEntity<ApiResponse> createNew(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addNew(productRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .data(productResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<ProductResponse> productResponse = productService.findAll(pr);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(productResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable("code") String code) {
        ProductResponse productResponse = productService.findOne(code);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(productResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @Override
    @PutMapping("/{code}")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable("code") String code, @RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.updateOne(code, request);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .data(productResponse)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
}
