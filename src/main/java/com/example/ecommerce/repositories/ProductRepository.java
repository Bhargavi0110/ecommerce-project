package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

   List<Product> findByProductCategory(ProductCategory productCategory);
   List<Product> findByProductPrice(int price);

//   List<Product> findByCategory(String category);

   List<Product> findByProductPriceBetween(double minPrice, double maxPrice);
}
