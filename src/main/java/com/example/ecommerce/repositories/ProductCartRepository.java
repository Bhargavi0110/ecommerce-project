package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.ProductCart;
import com.example.ecommerce.entities.ProductCategory;
import com.example.ecommerce.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart,Integer> {

    ProductCart findByUserInfo(UserInfo userInfo);
}
