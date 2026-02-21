package com.example.demo.product.controller;

import com.example.demo.product.dto.CategoryRequest;
import com.example.demo.product.dto.ProductsRequest;
import com.example.demo.product.service.CategoriesService;
import com.example.demo.product.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/create1")
    public ResponseEntity<Void> create(@RequestBody ProductsRequest productsRequest) {
        productsService.saveProducts(productsRequest);
        return ResponseEntity.ok().build();
    }

}
