package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    ProductCategory findByCategoryName(String category);
}
