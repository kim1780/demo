package com.example.demo.product.controller;

import com.example.demo.common.dto.Product;
import com.example.demo.common.dto.ProductRes;
import com.example.demo.common.service.ProductService;
import com.example.demo.product.dto.CategoryRequest;
import com.example.demo.product.service.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CategoryRequest categoryRequest) {
        categoriesService.saveCategories(categoryRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/read")
    public ResponseEntity<List<CategoryRequest>> getAlL() {
        return ResponseEntity.ok(categoriesService.findall());
    }


}
