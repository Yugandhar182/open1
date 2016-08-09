package com.ecommerce.controller;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by reloaded on 9.7.2016.
 */
@Controller
public class HomeController {

    private Path path;

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
    public String viewProduct(@PathVariable String productId, Model model) throws IOException{
            /* productId is grabbed to content of a product  */
        Product product = productDao.getProductById(productId);
        model.addAttribute(product);

        return "viewProduct";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping("/admin/productInventory")
    public String productInventory(Model model){
        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products",products);

        return "productInventory";
    }

    @RequestMapping("/admin/productInventory/addProduct")
    public String addProduct(Model model){
        Product product = new Product();
        product.setProductCategory("instrument");   // default values before adding product
        product.setProductCondition("new");
        product.setProductStatus("active");

        model.addAttribute(product);

        return "addProduct";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    // whenever product is added via post method, if information is filled and sent as a post request
    public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request){
        productDao.addProduct(product);

        MultipartFile productImage = product.getProductImage();
        String rootDirectory  = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId()+".png");

        if(productImage != null && !productImage.isEmpty()){
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed!", e);
            }
        }

        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/deleteProduct/{id}")
    // whenever product is added via post method, if information is filled and sent as a post request
    public String deleteProduct(@PathVariable String id, Model model, HttpServletRequest request){

        String rootDirectory  = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + id +".png");

        if(Files.exists(path)){
            try{
                Files.delete(path);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        productDao.deleteProduct(id);

        return "redirect:/admin/productInventory";
    }


    @RequestMapping("/admin/productInventory/editProduct/{id}")
    public String editProduct(@PathVariable("id") String id, Model model){
        Product product = productDao.getProductById(id);

        model.addAttribute(product);

        return "editProduct";
    }

    @RequestMapping(value = "/admin/productInventory/editProduct", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("product") Product product, Model model, HttpServletRequest request){

        MultipartFile productImage = product.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId()+".png");

        if(productImage != null && !productImage.isEmpty()){
            try{
                productImage.transferTo(new File(path.toString()));
            } catch (IOException e){
                throw new RuntimeException("Product Image saving failed!", e);
            }
        }

        productDao.editProduct(product);

        return "redirect:/admin/productInventory";
    }

}