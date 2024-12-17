package com.scaler.productservice.repositories;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductWithIdAndTitle;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll();


//    HQL
//    when we need to write the query by our own
    @Query("select p.id as id ,p.title as title from Product p where p.id =:x")
    List<ProductWithIdAndTitle> randomSearchMethod(Long x);

// SQL
    @Query(value = "select * from product p where p.id = :productId",nativeQuery = true)
    List<ProductWithIdAndTitle> randomSearchMethod2(Long productId);

}
