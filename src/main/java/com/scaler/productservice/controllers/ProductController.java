package com.scaler.productservice.controllers;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {

     private ProductService productService;
     public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
         this.productService = productService;
     }

     // https://localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
         ResponseEntity<Product> responseEntity = new ResponseEntity<>(
                 productService.getSingleProduct(id),
                 HttpStatus.OK
         );

         return responseEntity;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws ProductNotFoundException {
         return productService.getAllProduct(pageNumber,pageSize);
    }

    public void deleteProduct(long id){

    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id,@RequestBody Product product) throws ProductNotFoundException {
         return productService.updateProduct(id,product);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id,@RequestBody Product product){
         return null;
    }


    @PostMapping
    public Product addNewProduct(@RequestBody Product product){

         return productService.addNewProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
         productService.deleteProduct(productId);
    }
}
