package com.ecommerce.dao.impl;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;

/**
 * Created by reloaded on 24.7.2016.
 */
// controller repository, service annotations of spring
// whenever spring sees the annotation spring scans the class
// repository means it is some class related to the database
@Repository
@Transactional
// transactional annotation allows hibernate know how to deal with
public class ProductDaoImpl implements ProductDao{

    @Autowired
    // injects the bean to the container, so that we use it
    private SessionFactory sessionFactory;
    // sessionFactory bean is defined

    public void addProduct(Product product){
        Session session = sessionFactory.getCurrentSession();
        // sessionfactory is like a singleton, like a single bean
        // you need to call it wherever you use it
        session.saveOrUpdate(product);
        session.flush();
    }

    public void editProduct(Product product){
        Session session = sessionFactory.getCurrentSession();
        // sessionfactory is like a singleton, like a single bean
        // you need to call it wherever you use it
        session.saveOrUpdate(product);
        session.flush();
    }

    public Product getProductById(int id){
        Session session = sessionFactory.getCurrentSession();
        Product product = (Product) session.get(Product.class, id);
        session.flush();

        return product;
    }

    public List<Product> getAllProducts(){
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Product";
        Query query = session.createQuery(hql);
        List<Product> products = query.list();
        session.flush();

        return  products;
    }

    public void deleteProduct(int id){
        Session session = sessionFactory.getCurrentSession();
        session.delete(getProductById(id));
        session.flush();
    }
}
