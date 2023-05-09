package com.example.ecommerce.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="order_details")
public class ProductOrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private ProductPayment productPayment;

    @Column(name="order_amount",precision = 10,scale = 2)
    private Double orderAmount;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="order_date")
    private LocalDate orderDate;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ProductPayment getProductPayment() {
        return productPayment;
    }

    public void setProductPayment(ProductPayment productPayment) {
        this.productPayment = productPayment;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public ProductOrderDetails() {
    }

    public ProductOrderDetails(Integer orderId, UserInfo userInfo, ProductPayment productPayment, Double orderAmount, String orderStatus, LocalDate orderDate) {
        this.orderId = orderId;
        this.userInfo = userInfo;
        this.productPayment = productPayment;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
    }
}
