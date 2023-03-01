package com.minty.salesinventorymgt.services;

import com.minty.salesinventorymgt.dtos.request.OrderRequest;
import com.minty.salesinventorymgt.dtos.response.OrderResponse;
import com.minty.salesinventorymgt.models.Order;
import org.springframework.data.domain.PageRequest;

import java.text.ParseException;
import java.util.List;

public interface AppService<E, In, Out> {

    boolean isExist(String uniqueKey);

     E findOneEntity(String uniqueKey);
     Out findOne(String uniqueKey);

     Out addNew(In request) ;

    List<Out> findAll(PageRequest pageRequest);

    Out updateOne(String uniqueKey, In request);

}
