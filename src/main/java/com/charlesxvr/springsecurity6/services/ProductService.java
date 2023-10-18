package com.charlesxvr.springsecurity6.services;


import com.charlesxvr.springsecurity6.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product newProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
