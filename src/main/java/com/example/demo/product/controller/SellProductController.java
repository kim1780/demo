package com.example.demo.product.controller;

import com.example.demo.product.dto.SellProductRequest;
import com.example.demo.product.service.SellProductService;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/sell")
@CrossOrigin(origins = "http://localhost:5176")
public class SellProductController {

    private final SellProductService sellProductService;

    public SellProductController(SellProductService sellProductService) {
        this.sellProductService = sellProductService;
    }

    @PostMapping
    public Map<String, Object> sellProduct(@RequestBody SellProductRequest request) {
        return sellProductService.sellProduct(request);
    }
}