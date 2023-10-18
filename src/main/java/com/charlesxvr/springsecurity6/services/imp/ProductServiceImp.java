package com.charlesxvr.springsecurity6.services.imp;

import com.charlesxvr.springsecurity6.models.Product;
import com.charlesxvr.springsecurity6.repository.ProductRepository;
import com.charlesxvr.springsecurity6.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }

    @Override
    public Product newProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product currentProduct = this.productRepository.findById(id).orElse(null);
        if (currentProduct != null) {
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            return this.productRepository.save(currentProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }
}
