package com.ecommerce.dao;

import com.ecommerce.model.Product;

import java.util.List;

/**
 * Created by reloaded on 24.7.2016.
 */
public interface ProductDao {

    void addProduct(Product product);

    void editProduct(Product product);

    Product getProductById(String id);

    List<Product> getAllProducts();

    void deleteProduct(String id);
}
