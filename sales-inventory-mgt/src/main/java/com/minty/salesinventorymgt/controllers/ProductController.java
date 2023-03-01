package com.minty.salesinventorymgt.controllers;

import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.models.Product;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController implements AppController<ProductRequest, ProductResponse> {

    final AppService<Product, ProductRequest, ProductResponse> productService;

    @Override
    @PostMapping
    public ResponseEntity<ProductResponse> createNew(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.addNew(productRequest);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }
    @Override
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size){
        PageRequest pr = PageRequest.of(page, size, Sort.by( Sort.Direction.DESC,"createdDate"));
        List<ProductResponse> productResponse = productService.findAll(pr);
        return new ResponseEntity<List<ProductResponse>>(productResponse, HttpStatus.OK);
    }
    @Override
    @GetMapping("/{code}")
    public ResponseEntity<ProductResponse> getOne(@PathVariable("code") String code){
        ProductResponse productResponse = productService.findOne(code);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }
    @Override
    @PutMapping("/{code}")
    public ResponseEntity<ProductResponse> updateOne(@PathVariable("code") String code, @RequestBody ProductRequest request){
        ProductResponse productResponse = productService.updateOne(code, request);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }
}
