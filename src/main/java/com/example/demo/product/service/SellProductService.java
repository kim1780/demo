package com.example.demo.product.service;

import com.example.demo.product.dto.SellProductRequest;
import com.example.demo.product.entity.ProductsEntity;
import com.example.demo.product.entity.SaleHistoryEntity;
import com.example.demo.product.repository.ProductsRepository;
import com.example.demo.product.repository.SaleHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SellProductService {

    private final ProductsRepository productsRepository;
    private final SaleHistoryRepository saleHistoryRepository; // 1. ประกาศตัวแปร

    // 2. ยัดใส่ Constructor
    public SellProductService(ProductsRepository productsRepository, SaleHistoryRepository saleHistoryRepository) {
        this.productsRepository = productsRepository;
        this.saleHistoryRepository = saleHistoryRepository;
    }

    public List<ProductsEntity> getAllProducts() {
        return productsRepository.findAll();
    }

    @Transactional
    public Map<String, Object> sellProduct(SellProductRequest request) {
        Map<String, Object> response = new HashMap<>();

        if (request.getItems() == null || request.getItems().isEmpty()) {
            response.put("error", "ไม่มีสินค้าในตะกร้า");
            return response;
        }

        if (request.getMoney() == null) {
            response.put("error", "กรุณาใส่จำนวนเงิน");
            return response;
        }

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<ProductsEntity> productsToUpdate = new ArrayList<>();
        List<SaleHistoryEntity> historyToSave = new ArrayList<>(); // List สำหรับเก็บประวัติ

        for (SellProductRequest.Item item : request.getItems()) {
            ProductsEntity product = productsRepository.findById(item.getProductId()).orElse(null);

            if (product == null) {
                response.put("error", "ไม่พบสินค้ารหัส: " + item.getProductId());
                return response;
            }

            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                response.put("error", "จำนวนสินค้าไม่ถูกต้อง");
                return response;
            }

            if (product.getStock() < item.getQuantity()) {
                response.put("error", "สต็อกไม่พอ: " + product.getName());
                return response;
            }

            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);

            // ตัดสต็อก
            product.setStock(product.getStock() - item.getQuantity());
            productsToUpdate.add(product);

            // 3. สร้างข้อมูลประวัติการขาย (อิงตาม Entity มึงเป๊ะๆ)
            SaleHistoryEntity history = new SaleHistoryEntity();
            history.setProductName(product.getName());
            history.setQuantity(item.getQuantity());
            history.setTotalPrice(itemTotal);
            history.setDateTime(LocalDateTime.now()); // เซฟเวลาปัจจุบัน

            historyToSave.add(history);
        }

        if (request.getMoney().compareTo(totalPrice) < 0) {
            response.put("error", "เงินไม่พอ! ยอดรวมคือ " + totalPrice);
            return response;
        }

        // 4. บันทึกลง Database
        productsRepository.saveAll(productsToUpdate);
        saleHistoryRepository.saveAll(historyToSave); // เซฟประวัติลงตาราง sale_history

        BigDecimal change = request.getMoney().subtract(totalPrice);

        response.put("success", true);
        response.put("totalPrice", totalPrice);
        response.put("change", change);
        response.put("message", "ชำระเงินสำเร็จ!");

        return response;
    }
}