package com.example.demo.product.service;

import com.example.demo.product.dto.ProductsRequest;
import com.example.demo.product.entity.ProductsEntity;
import com.example.demo.product.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public void saveProducts(ProductsRequest productsRequest){

        ProductsEntity productsEntity = new ProductsEntity();

        productsEntity.setCategoryId(productsRequest.getCategoryId());
        productsEntity.setName(productsRequest.getName());
        productsEntity.setDescription(productsRequest.getDescription());
        productsEntity.setPrice(productsRequest.getPrice());
        productsEntity.setStock(productsRequest.getStock());

        productsRepository.save(productsEntity);
    }

    public List<ProductsRequest> findall() {

        List<ProductsRequest> productsRequestList = new ArrayList<>();
        List<ProductsEntity> productsEntities =
                (List<ProductsEntity>) productsRepository.findAll();

        for (ProductsEntity entity : productsEntities) {

            ProductsRequest productsRequest = new ProductsRequest();

            productsRequest.setId(entity.getId());
            productsRequest.setCategoryId(entity.getCategoryId());
            productsRequest.setName(entity.getName());
            productsRequest.setDescription(entity.getDescription());
            productsRequest.setPrice(entity.getPrice());
            productsRequest.setStock(entity.getStock());

            productsRequestList.add(productsRequest);
        }

        return productsRequestList;
    }
}