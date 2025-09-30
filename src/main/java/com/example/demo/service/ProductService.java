package com.example.demo.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    public List<Product> getAll() {
        return productRepository.findAll();
    }
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product update(Long id, Product updated) {
        return productRepository.findById(id)
                .map(p -> {
                    p.setTitle(updated.getTitle());
                    p.setCost(updated.getCost());
                    return productRepository.save(p);
                })
                .orElse(null);
    }
    public boolean delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<Product> getByTitle (String title) {
        if (title != null) {
            return productRepository.findByTitleContainingIgnoreCase(title);
        }
        return productRepository.findAll();
    } 
}