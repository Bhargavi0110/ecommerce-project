package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductCategory;
import com.example.ecommerce.entities.ProductOrderDetails;
import com.example.ecommerce.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderDetailsRepository extends JpaRepository<ProductOrderDetails,Integer> {

    List<ProductOrderDetails> findByUserInfo(UserInfo userInfo);
    ProductOrderDetails findByOrderIdAndUserInfo(Integer orderid,UserInfo userInfo);
}
