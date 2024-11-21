package com.scaler.productservice.controllers;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

     // https://localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id){
         ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                 productService.getSingleProduct(id),
                 HttpStatus.OK
         );

         return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
         return productService.getAllProduct();
    }

    public void deleteProduct(long id){

    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id,@RequestBody Product product){
         return productService.updateProduct(id,product);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id,@RequestBody Product product){
         return null;
    }
}
