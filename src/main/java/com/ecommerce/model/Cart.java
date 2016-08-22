package com.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by reloaded on 21.8.2016.
 */
public class Cart {
    private String cartId;
    private Map<String, CartItem> cartItems;
    private double grandTotal;

    // empty constructor
    private Cart(){
        cartItems = new HashMap<String, CartItem>();
        grandTotal = 0;
    }

    // another constructor
    public Cart(String cartId){
        this();
        this.cartId = cartId;
    }

    ////////////////////////////////////// getter and setts start
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
    ////////////////////////////////////// getter and setters finish

    public void addCartItem (CartItem item){
        String productId = item.getProduct().getProductId().toString();

        // check if hashmap has this product id
        if(cartItems.containsKey(productId)){
            CartItem existingCartItem = cartItems.get(productId);
            existingCartItem.setQuantity(existingCartItem.getQuantity()+item.getQuantity());
            cartItems.put(productId,existingCartItem);
            // update the hash map element with new item
        } else {
            cartItems.put(productId,item);
        }

        updateGrandTotal();
    }

    public void removeCartItem (CartItem item){
        String productId = item.getProduct().getProductId().toString();
        cartItems.remove(productId);
        updateGrandTotal();
    }

    public void updateGrandTotal(){
        grandTotal = 0;
        for (CartItem item: cartItems.values()){
            grandTotal = grandTotal + item.getTotalPrice();
        }
    }
}
