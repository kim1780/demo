package com.example.demo.common.repository;

import com.example.demo.common.entity.BasketEntity;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository <BasketEntity , Long> {
}
