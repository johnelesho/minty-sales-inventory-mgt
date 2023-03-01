package com.minty.salesinventorymgt.controllers;

import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AppController<In, Out> {
    ResponseEntity<Out> createNew( In request);


    ResponseEntity<List<Out>> getAll( int page,
                                                 int size);


    ResponseEntity<Out> getOne( String code);

    ResponseEntity<Out> updateOne(String code,In request);
}
