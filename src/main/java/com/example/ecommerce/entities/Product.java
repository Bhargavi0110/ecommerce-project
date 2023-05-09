package com.example.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Column(name="productName")
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="product_price",precision = 10,scale = 2)
    private Double productPrice;

    @OneToOne
    @JoinColumn(name="inventory_id")
    private ProductInventory productInventory;

    @ManyToOne
    @JoinColumn(name="discount_id")
    private ProductDiscount productDiscount;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ProductImages productImages;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductInventory getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(ProductInventory productInventory) {
        this.productInventory = productInventory;
    }

    public ProductDiscount getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(ProductDiscount productDiscount) {
        this.productDiscount = productDiscount;
    }

    public ProductImages getProductImages() {
        return productImages;
    }

    public void setProductImages(ProductImages productImages) {
        this.productImages = productImages;
    }

    public Product() {
    }

    public Product(Integer productId, ProductCategory productCategory, String productName, String productDescription, Double productPrice, ProductInventory productInventory, ProductDiscount productDiscount, ProductImages productImages) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productInventory = productInventory;
        this.productDiscount = productDiscount;
        this.productImages = productImages;
    }
}
