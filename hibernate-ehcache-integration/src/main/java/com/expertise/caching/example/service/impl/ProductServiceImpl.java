package com.expertise.caching.example.service.impl;

import com.expertise.caching.example.model.Product;
import com.expertise.caching.example.repository.ProductRepository;
import com.expertise.caching.example.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        // First-level cache demo
        System.out.println("First call - may hit database");
        Product p1 = productRepository.findById(id);

        System.out.println("Second call - first-level cache hit");
        Product p2 = productRepository.findById(id);

        System.out.println("Same instance? " + (p1 == p2));

        return p1;
    }

    @Override
    @Cacheable(value = "products")
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        System.out.println("Fetching all products from database");
        return productRepository.findAll();
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    @Transactional
    public void clearProductCache() {
        System.out.println("Cleared products cache");
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}