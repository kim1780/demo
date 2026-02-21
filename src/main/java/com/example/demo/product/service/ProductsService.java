package com.example.demo.product.service;

import com.example.demo.product.dto.CategoryRequest;
import com.example.demo.product.dto.ProductsRequest;
import com.example.demo.product.entity.CategoriesEntity;
import com.example.demo.product.entity.ProductsEntity;
import com.example.demo.product.repository.CategoriesRepository;
import com.example.demo.product.repository.ProductsRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    public void saveProducts(ProductsRequest productsRequest){
        ProductsEntity productsEntity = new ProductsEntity();
        productsEntity.setCategory_id(productsRequest.getCategory_id());
        productsEntity.setName(productsRequest.getName());
        productsEntity.setDescription(productsRequest.getDescription());
        productsEntity.setPrice(productsRequest.getPrice());
        productsEntity.setStock(productsRequest.getStock());
        productsRepository.save(productsEntity);
    }

}
