package com.example.store_mobile.services;

import com.example.store_mobile.models.Product;
import com.example.store_mobile.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productsRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productsRepository.save(product);
    }

    public Product updateProduct(int id, Product productDetails, MultipartFile imgProduct) {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Cập nhật các trường thông tin sản phẩm
        product.setName_product(productDetails.getName_product());
        product.setBrand_product(productDetails.getBrand_product());
        product.setPrice_product(productDetails.getPrice_product());

        // Nếu có ảnh mới, cập nhật ảnh
        if (!imgProduct.isEmpty()) {
            String imageName = saveImage(imgProduct); // Lưu ảnh mới
            product.setImg_product(imageName); // Cập nhật ảnh sản phẩm
        }

        return productsRepository.save(product);
    }

    // Hàm để lưu ảnh vào thư mục
    private String saveImage(MultipartFile file) {
        try {
            String uploadDir = "src/main/resources/static/img/";  // Đường dẫn lưu ảnh
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            file.transferTo(dest); // Lưu ảnh vào thư mục
            return "/img/" + fileName;  // Trả về đường dẫn ảnh
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteProduct(int id) {
        if (productsRepository.existsById(id)) {
            productsRepository.deleteById(id);
            System.out.println("Product with ID " + id + " deleted successfully.");
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

}
