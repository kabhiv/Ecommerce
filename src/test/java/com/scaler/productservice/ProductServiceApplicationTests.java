package com.scaler.productservice;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdAndTitle;
import com.scaler.productservice.repositories.CategoryRepository;
import com.scaler.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;

//	@Test
//	void contextLoads() {
//	}

	@Test
	void testDbQueries(){
//		List<ProductWithIdAndTitle> productWithIdAndTitles = productRepository.randomSearchMethod2(1L);
//
//		for(ProductWithIdAndTitle product : productWithIdAndTitles){
//			System.out.println(product.getId()+" "+ product.getTitle());
//		}

		Optional<Product> product = productRepository.findById(1L);

		Optional<Category> optionalCategory	= categoryRepository.findById(1L);

		List<Product> products = optionalCategory.get().getProducts();

		System.out.println("DEBUG");
	}

}
