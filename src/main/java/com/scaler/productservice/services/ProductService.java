package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    Page<Product> getAllProduct(int PageNumber , int PageSize);

//    List<Product> getAllProduct();

    Product updateProduct(Long id , Product product) throws ProductNotFoundException;

    Product replaceProduct(Long id ,Product product);

    void deleteProduct(Long id);

    Product addNewProduct(Product product);
}
