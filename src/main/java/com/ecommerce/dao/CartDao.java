package com.ecommerce.dao;

import com.ecommerce.model.Cart;

/**
 * Created by reloaded on 21.8.2016.
 */
public interface CartDao {

    Cart create(Cart cart);

    Cart read(String cartId);

    void update(String cartId, Cart cart);

    void delete(String cartId);
}
