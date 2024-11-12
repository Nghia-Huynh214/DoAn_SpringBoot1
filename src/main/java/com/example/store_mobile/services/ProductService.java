package com.example.store_mobile.services;

import com.example.store_mobile.models.Product;
import com.example.store_mobile.repository.ProductsRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProductById(long id) {
        return productsRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productsRepository.save(product);
    }

    public Product updateProduct(long id, Product productDetails) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName_product(productDetails.getName_product());
        product.setBrand_product(productDetails.getBrand_product());
        return productsRepository.save(product);
    }

    public void deleteProduct(long id) {
        productsRepository.deleteById(id);
    }

}
