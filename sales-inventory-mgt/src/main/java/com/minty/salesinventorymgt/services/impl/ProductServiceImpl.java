package com.minty.salesinventorymgt.services.impl;

import com.minty.salesinventorymgt.HelpMapper;
import com.minty.salesinventorymgt.dtos.request.ProductRequest;
import com.minty.salesinventorymgt.dtos.response.ProductResponse;
import com.minty.salesinventorymgt.exceptions.BadRequestException;
import com.minty.salesinventorymgt.exceptions.NotFoundException;
import com.minty.salesinventorymgt.models.Order;
import com.minty.salesinventorymgt.models.Product;
import com.minty.salesinventorymgt.repositories.ProductRepository;
import com.minty.salesinventorymgt.services.AppService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements AppService<Product, ProductRequest, ProductResponse> {

    final ProductRepository productRepository;

    final HelpMapper helpMapper;

    @Value("${productCode.length:7}")
    private int productCodeLength;
    @Override
    public boolean isExist(String uniqueKey) {
        return productRepository.existsByCodeIgnoreCase(uniqueKey);

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
    public ProductResponse addNew(ProductRequest request) {
        Product product = null;
        try {
            product = helpMapper.convertToProductEntity(request);
        } catch (ParseException e) {
            throw new BadRequestException(e.getMessage());
        }
        StringBuilder code = new StringBuilder(productCodeLength);
        code.append(request.getName().substring(0,4));
        boolean exist = true;
        while (exist){
            code.append(RandomStringUtils.randomAlphanumeric(productCodeLength));
            exist = isExist(code.toString());
        }
        code.append(RandomStringUtils.randomNumeric(productCodeLength));
        product.setCode(code.toString());

        product = productRepository.save(product);
        return helpMapper.convertToProductResonse(product);
    }

    @Override
    public List<ProductResponse> findAll(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).stream().map(helpMapper::convertToProductResonse).toList();
    }

    @Override
    public ProductResponse updateOne(String uniqueKey, ProductRequest request) {
        Product entity = findOneEntity(uniqueKey);
        BeanUtils.copyProperties(request, entity);
        return helpMapper.convertToProductResonse(entity);
    }
}
