package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@RestController
public class MainController {
    private ProductService productService;
    public MainController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    private List<Product> getProducts(){
        return productService.getAll();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        for (Product product : productService.getAll()) {
            if (product.getId().equals(id)) {
                return ResponseEntity.ok(product);
            } 
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/products")
    private ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        product.setId(3l);
        productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}