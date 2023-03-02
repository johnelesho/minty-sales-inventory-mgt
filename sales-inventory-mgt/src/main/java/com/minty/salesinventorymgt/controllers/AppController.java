package com.minty.salesinventorymgt.controllers;


import com.minty.lib.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AppController<In> {
    ResponseEntity<ApiResponse> createNew(In request);


    ResponseEntity<ApiResponse> getAll(int page,
                                       int size);


    ResponseEntity<ApiResponse> getOne(String code);

    ResponseEntity<ApiResponse> updateOne(String code, In request);
}
