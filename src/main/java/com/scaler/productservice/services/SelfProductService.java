package com.scaler.productservice.services;

import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.categoryRepository=categoryRepository;
    }



    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
//        make a call to DB to fetch a product with given id.

        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product not found 1");
        }
        return productOptional.get();
    }

//    @Override
//    public List<Product> getAllProduct(int PageNumber, int PageSize) throws ProductNotFoundException {
//        return List.of();
//    }

    @Override
    public Page<Product> getAllProduct(int pageNumber, int pageSize) {

//        Sort sort= Sort.by("price").ascending().and(Sort.by("title").descending());
        return productRepository
                .findAll(PageRequest.of(pageNumber, pageSize,Sort.by("price").ascending()));


    }

//    @Override
//    public List<Product> getAllProduct() {
//        return List.of();
//    }


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
//
//        if(category.getId()== null){
//            category= categoryRepository.save(category);
//            product.setCategory(category);
//        }

       return productRepository.save(product);
    }
}
