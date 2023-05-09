package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProductOrderDetails;
import com.example.ecommerce.entities.ProductOrderItems;
import com.example.ecommerce.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderItemsRepository extends JpaRepository<ProductOrderItems,Integer> {

    @Query(value = "SELECT * FROM order_items as o WHERE o.order_id IN (:productOrderDetails)",nativeQuery = true)
    List<ProductOrderItems> findByListOfProductOrderDetails(List<ProductOrderDetails> productOrderDetails);

    List<ProductOrderItems> findByProductOrderDetails(ProductOrderDetails productOrderDetails);
}
