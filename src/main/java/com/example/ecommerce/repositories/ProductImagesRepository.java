package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages,Integer> {

    ProductImages findByName(String fileName);
}


