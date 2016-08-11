package com.ecommerce.dao;

import com.ecommerce.model.Product;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by reloaded on 24.7.2016.
 */
public interface ProductDao {

    void addProduct(Product product);

    void editProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    void deleteProduct(int id);
}
