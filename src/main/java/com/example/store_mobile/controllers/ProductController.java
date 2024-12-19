package com.example.store_mobile.controllers;


import com.example.store_mobile.models.Product;
import com.example.store_mobile.repository.ProductsRepository;
import com.example.store_mobile.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsRepository repo;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/home")
    public String homeProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/home";
    }

    @GetMapping("/admin")
    public String adminProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/admin";
    }

    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        return productsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        return productsRepository.findById(id).map(product -> {
            product.setImg_product(updatedProduct.getImg_product());
            product.setBrand_product(updatedProduct.getBrand_product());
            product.setName_product(updatedProduct.getName_product());
            product.setPrice_product(updatedProduct.getPrice_product());
            productsRepository.save(product);
            return ResponseEntity.ok("Product updated successfully!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        if (productService.getProductById(id).isPresent()) { // Kiểm tra sản phẩm tồn tại
            productService.deleteProduct(id); // Xóa sản phẩm qua service
            return ResponseEntity.ok("Product deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cart")
    public String showProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/cart";
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product()); // Đảm bảo tạo đối tượng Product
        return "products/create";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productsRepository.save(product);
        return "redirect:/products/admin";
    }


    @GetMapping("/apple")
    public String appleProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/apple";
    }

    @GetMapping("/samsung")
    public String samsungProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/samsung";
    }

    @GetMapping("/oppo")
    public String oppoProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/oppo";
    }

    @GetMapping("/edit")
    public String editProduct(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/edit";
    }

}