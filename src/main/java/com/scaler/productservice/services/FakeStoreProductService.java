package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService {


    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+productId, FakeStoreProductDto.class);

        return convertFakeStoreProductToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
