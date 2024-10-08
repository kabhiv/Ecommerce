package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

     private ProductService productService;

     public ProductController(ProductService productService){

         this.productService = productService;
     }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") long id){

         return productService.getSingleProduct(id);
    }


    @GetMapping()
    public List<Product> getAllProducts(){

         return productService.getAllProduct();
    }

    public void deleteProduct(Long productId){

    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id")  Long id , @RequestBody Product product){

         return productService.updateProduct(id , product);

    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id ,@RequestBody Product product){

         return null;

    }






}
