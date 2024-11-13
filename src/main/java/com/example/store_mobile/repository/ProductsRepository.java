package com.example.store_mobile.repository;

import com.example.store_mobile.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

}