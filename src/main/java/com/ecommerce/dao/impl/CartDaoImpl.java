package com.ecommerce.dao.impl;

import com.ecommerce.dao.CartDao;
import com.ecommerce.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by reloaded on 21.8.2016.
 */
@Repository // allows to autowire, register this class as a bean
public class CartDaoImpl implements CartDao {

    private Map<String, Cart> listOfCarts;

    // adding contructor to initialize list of cards as hashmap
    public CartDaoImpl(){
        listOfCarts = new HashMap<String, Cart>();
    }

    public Cart create(Cart cart){
        if(listOfCarts.keySet().contains(cart.getCartId())){
            throw new IllegalArgumentException(String.format("Cannot create a cart, cartId(%) " + "is already used!", cart.getCartId() ));
        }

        listOfCarts.put(cart.getCartId(), cart);

        return cart;
    }

    public Cart read(String cartId){
        return listOfCarts.get(cartId);
    }

    public void update(String cartId, Cart cart){
        if(!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("Cannot update the cart with cartId(%) " + "doesn't exist!", cart.getCartId() ));
        }

        listOfCarts.put(cartId,cart);
    }

    public void delete(String cartId){
        if(!listOfCarts.keySet().contains(cartId)){
            throw new IllegalArgumentException(String.format("Cannot delete the cart with cartId(%) " + "doesn't exist!", cartId ));
        }

        listOfCarts.remove(cartId);
    }
}
