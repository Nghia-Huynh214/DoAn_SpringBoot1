package com.example.store_mobile.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController {
    @GetMapping("products/home")
    public String home() {
        return "products/home";
    }

    @GetMapping("products/create")
    public String create() {
        return "products/create";
    }

    @GetMapping("products/admin")
    public String admin() {
        return "products/admin";
    }

    @GetMapping("products/cart")
    public String cart() {
        return "products/cart";
    }

    @GetMapping("products/apple")
    public String apple() {
        return "products/apple";
    }

    @GetMapping("products/samsung")
    public String samsung() {
        return "products/samsung";
    }

    @GetMapping("products/oppo")
    public String oppo() {
        return "products/oppo";
    }

}
