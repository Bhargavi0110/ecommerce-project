package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProductPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPaymentRepository extends JpaRepository<ProductPayment,Integer> {
}
