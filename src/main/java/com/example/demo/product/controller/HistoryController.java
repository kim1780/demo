package com.example.demo.product.controller;

import com.example.demo.product.entity.SaleHistoryEntity;
import com.example.demo.product.repository.SaleHistoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@CrossOrigin(origins = "*") // อนุญาตให้ React ดึงข้อมูลได้
public class HistoryController {

    private final SaleHistoryRepository saleHistoryRepository;

    public HistoryController(SaleHistoryRepository saleHistoryRepository) {
        this.saleHistoryRepository = saleHistoryRepository;
    }

    @GetMapping
    public List<SaleHistoryEntity> getAllHistory() {
        return saleHistoryRepository.findAll();
    }
}