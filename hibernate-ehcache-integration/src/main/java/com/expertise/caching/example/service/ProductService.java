package com.expertise.caching.example.service;

import com.expertise.caching.example.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();

    void clearProductCache();

    void deleteProduct(Long id);
}