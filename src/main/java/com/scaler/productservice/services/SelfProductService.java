package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
//import com.scaler.productservice.repositories.ProductRepository;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }



    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//        make a call to DB to fetch a product with given id.

        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }


    //Patch
    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);


        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("no product available");
        }

        Product productInDB = optionalProduct.get();

        if(product.getTitle() != null){
            productInDB.setTitle(product.getTitle());
        }

        if(product.getPrice() != null){
            productInDB.setPrice(product.getPrice());
        }

        return productRepository.save(productInDB);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();

        if(category.getId()== null){
            category= categoryRepository.save(category);
            product.setCategory(category);
        }

       return productRepository.save(product);
    }
}
