package com.expertise.caching.example.repository;

import com.expertise.caching.example.model.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);
}