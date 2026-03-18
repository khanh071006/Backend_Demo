package com.example.Project2.service.Product;


import com.example.Project2.domain.Product;
import com.example.Project2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void hanldeSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void DeleteProductById(long id) {
        this.productRepository.deleteById(id);
    }
}
