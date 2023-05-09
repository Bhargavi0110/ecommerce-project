package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Integer> {
}
