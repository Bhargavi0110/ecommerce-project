package com.example.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name="cart_items")
public class ProductCartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name="cart_id")
    private ProductCart productCart;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="quantity")
    private Integer quantity;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public ProductCart getProductCart() {
        return productCart;
    }

    public void setProductCart(ProductCart productCart) {
        this.productCart = productCart;
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

    public ProductCartItems() {
    }

    public ProductCartItems(Integer itemId, ProductCart productCart, Product product, Integer quantity) {
        this.itemId = itemId;
        this.productCart = productCart;
        this.product = product;
        this.quantity = quantity;
    }
}
