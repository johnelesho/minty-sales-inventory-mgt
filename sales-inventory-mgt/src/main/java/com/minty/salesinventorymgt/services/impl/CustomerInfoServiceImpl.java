package com.minty.salesinventorymgt.services.impl;


import com.minty.lib.dtos.request.CustomerInfoRequest;
import com.minty.lib.dtos.response.CustomerInfoResponse;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.CustomerInfo;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.CustomerInfoRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CustomerInfoServiceImpl implements AppService<CustomerInfo, CustomerInfoRequest, CustomerInfoResponse> {

    final CustomerInfoRepository customerInfoRepository;


    final HelpMapper helpMapper;

    @Override
    public boolean isExist(String uniqueKey) {
        return customerInfoRepository.existsByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(uniqueKey);
    }


    @Override
    public CustomerInfo findOneEntity(String uniqueKey) {
        return customerInfoRepository.findByEmailAddressIgnoreCaseOrPhoneNumberOrUsernameIgnoreCase(uniqueKey).orElseThrow(() -> new NotFoundException("Customer does not exist"));

    }

    @Override
    public CustomerInfoResponse findOne(String uniqueKey) {
        CustomerInfo customerInfo = findOneEntity(uniqueKey);
        return helpMapper.convertToCustomerResonse(customerInfo);
    }

    @Override
    @Synchronized
    public CustomerInfoResponse addNew(CustomerInfoRequest request) {
        log.info("Add New Customer method");
        CustomerInfo customerInfo;
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        if (isExist(request.getEmailAddress())
                || isExist(request.getPhoneNumber())
                || isExist(request.getUsername())) {
            throw new BadRequestException("Customer Already Exist");
        }

        try {

            customerInfo = helpMapper.convertToCustomerEntity(request);
            customerInfo = customerInfoRepository.save(customerInfo);
            return helpMapper.convertToCustomerResonse(customerInfo);
        } catch (ParseException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<CustomerInfoResponse> findAll(PageRequest pageRequest) {
        return customerInfoRepository.findAll(pageRequest).stream().map(helpMapper::convertToCustomerResonse).toList();

    }


    @Override
    @Synchronized
    public CustomerInfoResponse updateOne(String uniqueKey, CustomerInfoRequest request) {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        CustomerInfo customerInfo = findOneEntity(uniqueKey);
        if (customerInfo == null) {
            throw new NotFoundException("Customer not found");
        }
        BeanUtils.copyProperties(request, customerInfo);
        return helpMapper.convertToCustomerResonse(customerInfo);
    }
}
