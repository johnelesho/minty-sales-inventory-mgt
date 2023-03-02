package com.minty.salesinventorymgt.services.impl;


import com.minty.lib.dtos.request.ProductRequest;
import com.minty.lib.dtos.response.ProductResponse;
import com.minty.lib.mappers.HelpMapper;
import com.minty.lib.models.Product;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.repositories.ProductRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements AppService<Product, ProductRequest, ProductResponse> {

    final ProductRepository productRepository;

    final HelpMapper helpMapper;

    @Value("${productCode.length:7}")
    private int productCodeLength;

    @Override
    public boolean isExist(String uniqueKey) {
        return productRepository.existsByCodeIgnoreCase(uniqueKey);

    }

    public boolean isExistByName(String name) {
        return productRepository.existsByNameIgnoreCase(name);

    }

    @Override
    public Product findOneEntity(String uniqueKey) {
        return productRepository.findByCodeIgnoreCase(uniqueKey).orElseThrow(() -> new NotFoundException("Product does not exist"));

    }

    @Override
    public ProductResponse findOne(String uniqueKey) {
        Product product = findOneEntity(uniqueKey);
        return helpMapper.convertToProductResonse(product);
    }

    @Override
    @Synchronized
    public ProductResponse addNew(ProductRequest request) {
        Product product = null;
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        if (isExistByName(request.getName())) {
            throw new BadRequestException("Product already exists");
        }
        try {
            product = helpMapper.convertToProductEntity(request);
        } catch (ParseException e) {
            throw new BadRequestException(e.getMessage());
        }
        String code = generateProductCode(request);
        product.setCode(code);

        product = productRepository.save(product);
        return helpMapper.convertToProductResonse(product);
    }


    public String generateProductCode(ProductRequest request) {
        StringBuilder code = new StringBuilder(productCodeLength);
        String codePrefix = request.getName().substring(0, 4);
        boolean exist = true;
        String codeSuffice = "";
        while (exist) {
            code.setLength(0);
            codeSuffice = RandomStringUtils.randomAlphanumeric(4);
            code.append(codePrefix);
            code.append(codeSuffice);
            exist = isExist(code.toString());
        }
        return code.toString();
    }

    @Override
    public List<ProductResponse> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).stream().map(helpMapper::convertToProductResonse).toList();
    }

    @Override
    @Synchronized
    public ProductResponse updateOne(String uniqueKey, ProductRequest request) {
        if (request == null) {
            throw new BadRequestException("Request cannot be null");
        }
        Product entity = findOneEntity(uniqueKey);
        if (entity == null) {
            throw new BadRequestException("Product not found");
        }
        BeanUtils.copyProperties(request, entity);
        return helpMapper.convertToProductResonse(entity);
    }
}
