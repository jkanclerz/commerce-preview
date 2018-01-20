package pl.jkan.webcommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import pl.jkan.ecommerce.sales.application.AddProductHandler;
import pl.jkan.ecommerce.sales.domain.basket.NotEnoughQuantityOnStock;
import pl.jkan.ecommerce.sales.domain.basket.Product;
import pl.jkan.ecommerce.sales.domain.product.ProductStore;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductStore productStore;

    @Autowired
    AddProductHandler addProductHandler;

    @RequestMapping("/products")
    @ResponseBody
    public List<Product> productsList() {
        return productStore.findAll();
    }

    @RequestMapping("/add-to-basket/{id}")
    public String addToBasket(@PathVariable(value="id") String productId) {
        try {
            addProductHandler.handle(productId);
            return "OK";
        } catch (NotEnoughQuantityOnStock e) {
            return "Something is not YES!!!!!!!!!";
        }
    }
}