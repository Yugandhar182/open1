package com.ecommerce.controller;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by reloaded on 9.7.2016.
 */
@Controller
public class HomeController {

    @Autowired
    // wire the bean related to product data model database operations
    private ProductDao productDao;
    /*
    * whenever there is a request to home return this
    * */
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/productList")
    public String getProducts(Model model){
        // function imports model parameter

        /* adding one product to attribute
        List<Product> productList = productDao.getProductList();

        Product product = productList.get(0);
        // return the product from the list
        model.addAttribute(product);
        // bind product object to the model. product model includes all the attributes
        // when the view is returned, the model will be attached to the view automatically
        */

        /* add multiple products*/
        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products", products);

        return "productList";
        // when returned the model will be attached automatically.
        // her zaman sayfa url adı return edilir
    }

    @RequestMapping("/productList/viewProduct/{productId}") /* productId is path variable, matching the path pattern*/
    public String viewProduct(@PathVariable("productId") int productId, Model model) throws IOException{
            /* productId is grabbed to content of a product  */
        Product product = productDao.getProductById(productId);
        model.addAttribute(product);

        return "viewProduct";
    }


}
