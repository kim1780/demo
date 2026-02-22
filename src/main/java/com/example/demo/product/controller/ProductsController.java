package com.example.demo.product.controller;

import com.example.demo.product.entity.ProductsEntity;
import com.example.demo.product.repository.ProductsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5176")
public class ProductsController {

    private final ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @GetMapping
    public List<ProductsEntity> getAllProducts() {
        return productsRepository.findAll();
    }
}