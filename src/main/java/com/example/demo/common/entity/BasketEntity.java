package com.example.demo.common.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "basket", schema = "public")
public class BasketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "customer")
    private String customer;

    @Column(name = "qty")
    private Integer qty;

    public static List<Object> findByid(Integer id) {
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    //-----------------------------------------------------------
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
    public Integer getItemId() {
        return itemId;
    }
    //-----------------------------------------------------------
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getCustomer() {
        return customer;
    }
    //-----------------------------------------------------------
    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public Integer getQty() {
        return qty;
    }
}