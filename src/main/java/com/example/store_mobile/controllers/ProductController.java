package com.example.store_mobile.controllers;


import com.example.store_mobile.models.Product;
import com.example.store_mobile.repository.ProductsRepository;
import com.example.store_mobile.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @GetMapping("/create")
//    public String createProductList(Model model) {
//        List<Product> products = repo.findAll();
//        model.addAttribute("products", products);
//        return "products/create";
//    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product()); // Đảm bảo tạo đối tượng Product
        return "products/create";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productsRepository.save(product);
        return "redirect:/products/home";
    }

    @GetMapping("/cart")
    public String showProductList(Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/cart";
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
}