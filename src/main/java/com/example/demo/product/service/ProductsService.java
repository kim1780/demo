package com.example.demo.product.service;

import com.example.demo.product.dto.CategoryRequest;
import com.example.demo.product.dto.ProductsRequest;
import com.example.demo.product.entity.CategoriesEntity;
import com.example.demo.product.entity.ProductsEntity;
import com.example.demo.product.repository.CategoriesRepository;
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
        productsEntity.setCategory_id(productsRequest.getCategory_id());
        productsEntity.setName(productsRequest.getName());
        productsEntity.setDescription(productsRequest.getDescription());
        productsEntity.setPrice(productsRequest.getPrice());
        productsEntity.setStock(productsRequest.getStock());
        productsRepository.save(productsEntity);
    }

    public List<ProductsRequest> findall() {
        List<ProductsRequest> productsRequestList = new ArrayList<>();
        List<ProductsEntity> productsEntities = (List<ProductsEntity>) productsRepository.findAll();


        for (int i = 0; i < productsEntities.size(); i++){
            ProductsRequest productsRequest = new ProductsRequest();
            productsRequest.setId(productsEntities.get(i).getId());
            productsRequest.setCategory_id(productsEntities.get(i).getCategory_id());
            productsRequest.setName(productsEntities.get(i).getName());
            productsRequest.setDescription(productsEntities.get(i).getDescription());
            productsRequest.setPrice(productsEntities.get(i).getPrice());
            productsRequest.setStock(productsEntities.get(i).getStock());
            productsRequestList.add(productsRequest);

        }
        return productsRequestList;
    }

}
