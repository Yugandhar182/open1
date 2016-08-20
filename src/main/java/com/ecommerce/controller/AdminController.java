package com.ecommerce.controller;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
// as long as this is imported, this admin controller class added to the container
/**
 * Created by reloaded on 20.8.2016.
 */
@Controller
public class AdminController {

    private Path path;

    @Autowired
    // wire the bean related to product data model database operations
    private ProductDao productDao;


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
        product.setProductCategory("software");   // default values before adding product
        product.setProductCondition("new");
        product.setProductStatus("active");

        model.addAttribute(product);

        return "addProduct";
    }

    @RequestMapping(value = "/admin/productInventory/addProduct", method = RequestMethod.POST)
    // whenever product is added via post method, if information is filled and sent as a post request
    public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){

        if(result.hasErrors()){
            return "addProduct";
        }

        productDao.addProduct(product);

        MultipartFile productImage = product.getProductImage();
        //String rootDirectory  = request.getSession().getServletContext().getRealPath("/");
        //path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId()+".png");
        path = Paths.get( System.getenv("OPENSHIFT_DATA_DIR") + "/images/" + product.getProductId()+".png");

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
    public String deleteProduct(@PathVariable("id") int id, Model model, HttpServletRequest request){

        //String rootDirectory  = request.getSession().getServletContext().getRealPath("/");
        //path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + id +".png");
    	path = Paths.get( System.getenv("OPENSHIFT_DATA_DIR") + "/images/" + product.getProductId()+".png");
        if(Files.exists(path)){
            try{
                Files.delete(path);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        productDao.deleteProduct(id);

        return "productInventory";
    }


    @RequestMapping("/admin/productInventory/editProduct/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        Product product = productDao.getProductById(id);

        model.addAttribute(product);

        return "editProduct";
    }

    @RequestMapping(value = "/admin/productInventory/editProduct", method = RequestMethod.POST)
    public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, HttpServletRequest request){

        if(result.hasErrors()){
            return "editProduct";
        }

        MultipartFile productImage = product.getProductImage();
        //String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        //path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\" + product.getProductId()+".png");
        path = Paths.get( System.getenv("OPENSHIFT_DATA_DIR") + "/images/" + product.getProductId()+".png");
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
