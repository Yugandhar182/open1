package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by reloaded on 21.8.2016.
 */
@Controller
@RequestMapping("/cart")    // this path is class level, so that it will be used in all function of this class automatically
public class CartItemController {

    @RequestMapping // at the url /cart
    public String get (HttpServletRequest request){
        return "redirect:/cart/" + request.getSession(true).getId();
        // session id will be used as cart id
    }

    @RequestMapping(value="/{cartId}", method = RequestMethod.GET)
    public String getCart(@PathVariable (value = "cartId") String cartId, Model model){
        model.addAttribute("cartId", cartId);

        return "cart";
    }
}
