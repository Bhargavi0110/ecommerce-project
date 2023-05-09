package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.ProductCart;
import com.example.ecommerce.entities.ProductCartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartItemsRepository extends JpaRepository<ProductCartItems,Integer> {

    List<ProductCartItems> findByProductCart(ProductCart productCart);

    ProductCartItems findByProduct(Product product);

    ProductCartItems findByProductCartAndProduct(ProductCart productCart,Product product);
}
