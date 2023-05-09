package com.example.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name="order_items")
public class ProductOrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="orderitemId")
    private Integer orderitemId;

    @ManyToOne
    @JoinColumn(name="order_id")
    private ProductOrderDetails productOrderDetails;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity")
    private Integer quantity;

    public Integer getOrderitemId() {
        return orderitemId;
    }

    public void setOrderitemId(Integer orderitemId) {
        this.orderitemId = orderitemId;
    }

    public ProductOrderDetails getProductOrderDetails() {
        return productOrderDetails;
    }

    public void setProductOrderDetails(ProductOrderDetails productOrderDetails) {
        this.productOrderDetails = productOrderDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductOrderItems() {
    }

    public ProductOrderItems(Integer orderitemId, ProductOrderDetails productOrderDetails, Product product, Integer quantity) {
        this.orderitemId = orderitemId;
        this.productOrderDetails = productOrderDetails;
        this.product = product;
        this.quantity = quantity;
    }
}
