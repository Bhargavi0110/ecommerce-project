package com.example.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name="cart")
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Integer cartId;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserInfo userInfo;

    @Column(name="total_amount",precision = 10,scale = 2)
    private Double totalAmount;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ProductCart() {
    }

    public ProductCart(Integer cartId, UserInfo userInfo, Double totalAmount) {
        this.cartId = cartId;
        this.userInfo = userInfo;
        this.totalAmount = totalAmount;
    }
}
