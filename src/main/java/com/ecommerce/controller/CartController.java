package com.ecommerce.controller;

import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by reloaded on 21.8.2016.
 */
@Controller // this controller providing restless services
@RequestMapping("/rest/cart")
public class CartController {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId){
        // response body allows to return a model object in format of JSON
        // cart object will be put into http response body
        // and then spring automatically parse that object in the JSON format
        // and send as a response body

        // retrieve path variable cartid, put it into new string, then access the cartdao

        // you could use @ResponseEntity gives more flexibility to control response, http response status as well
        return cartDao.read(cartId);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart){
        // request body takes the body of the request pass it and transfer into cart object
        // spring will automatically transfer between JSON and object format
        // when spring sees request body, automatically convert JSON into a cart object


        cartDao.update(cartId, cart);
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "cartId") String cartId) {

        cartDao.delete(cartId);
    }

    @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void addItem(@PathVariable(value = "productId") String productId, HttpServletRequest request){
        // define a session id first
        String sessionId = request.getSession(true).getId();
        Cart cart = cartDao.read(sessionId);

        if(cart == null){
            // if cart doesn't exist, create a new cart
            cart = cartDao.create(new Cart(sessionId));
        }

        Product product = productDao.getProductById(Integer.parseInt(productId));

        if(product == null){
            throw new IllegalArgumentException(new Exception());
        }

        cart.addCartItem(new CartItem(product));

        cartDao.update(sessionId, cart);
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable String productId, HttpServletRequest request){

        // define a session id first
        String sessionId = request.getSession(true).getId();
        Cart cart = cartDao.read(sessionId);

        if(cart == null){
            // if cart doesn't exist, create a new cart
            cart = cartDao.create(new Cart(sessionId));
        }

        Product product = productDao.getProductById(Integer.parseInt(productId));

        if(product == null){
            throw new IllegalArgumentException(new Exception());
        }


        cart.removeCartItem(new CartItem(product));

        cartDao.update(sessionId, cart);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal request, please verify your payload")
    public void handleClientError(Exception e){}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error, HTTP 500")
    public void handleServerError(Exception e){}
}
