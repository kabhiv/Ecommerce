package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

     private ProductService productService;
     public ProductController(ProductService productService){
         this.productService = productService;
     }

     // https://localhost:8080/products/10
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id){
         return productService.getSingleProduct(id);
    }

    @GetMapping()
    public List<Product> getAllProducts(){
         return productService.getAllProduct();
    }
}
