package com.example.store_mobile.api;



import com.example.store_mobile.models.Product;
import com.example.store_mobile.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductApiController {

    @Autowired
    private ProductsRepository productRepository;


    @GetMapping("/getall")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return productRepository.findById((int) id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (!productRepository.existsById((int) id)) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }


    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(201).body(savedProduct);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (!productRepository.existsById((int) id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById((int) id);
        return ResponseEntity.noContent().build();
    }

}
