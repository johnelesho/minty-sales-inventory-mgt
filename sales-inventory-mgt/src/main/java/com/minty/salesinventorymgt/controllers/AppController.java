package com.minty.salesinventorymgt.controllers;


import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AppController<In> {
    @PostMapping()
    ResponseEntity<ApiResponse> createNew(In request);


    @PostMapping("/bulk")
    ResponseEntity<ApiResponse> createNew(@RequestBody List<In> requests);

    ResponseEntity<ApiResponse> getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "10") int size);

    @GetMapping("/{code}")
    ResponseEntity<ApiResponse> getOne(String code);


    @PutMapping("/{code}")
    public ResponseEntity<ApiResponse> updateOne(@PathVariable("code") String code, @RequestBody In request);
}
