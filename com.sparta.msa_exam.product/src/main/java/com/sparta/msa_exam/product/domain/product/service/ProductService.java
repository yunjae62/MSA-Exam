package com.sparta.msa_exam.product.domain.product.service;

import com.sparta.msa_exam.product.domain.product.dto.request.AddProductReq;
import com.sparta.msa_exam.product.domain.product.dto.request.GetProductsReq;
import com.sparta.msa_exam.product.domain.product.dto.response.AddProductRes;
import com.sparta.msa_exam.product.domain.product.dto.response.GetProductRes;
import com.sparta.msa_exam.product.domain.product.entity.Product;
import com.sparta.msa_exam.product.domain.product.mapper.ProductMapper;
import com.sparta.msa_exam.product.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<GetProductRes> getProductList(GetProductsReq request) {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::toGetProductRes)
                .toList();
    }

    @Transactional
    public AddProductRes addProduct(AddProductReq request) {

        Product product = Product.from(request);
        Product savedProduct = productRepository.save(product);

        return ProductMapper.INSTANCE.toAddProductRes(savedProduct);
    }
}
