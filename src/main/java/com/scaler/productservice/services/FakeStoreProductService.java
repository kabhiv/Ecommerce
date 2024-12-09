package com.scaler.productservice.services;

import com.scaler.productservice.dtos.FakeStoreProductDto;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+productId, FakeStoreProductDto.class);
//

        if (fakeStoreProductDto == null){
            throw new ProductNotFoundException("product with id" + productId + "does not exist");
        }

        return convertFakeStoreProductToProduct(fakeStoreProductDto);


    }


    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return products;
    }
//Partial update i.e. Patch.
    @Override
    public Product updateProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product,FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());

        FakeStoreProductDto response= restTemplate.execute("https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(response);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

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
