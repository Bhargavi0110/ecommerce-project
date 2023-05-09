package com.example.ecommerce.dto;

import com.example.ecommerce.entities.ProductCategory;
import com.example.ecommerce.entities.ProductDiscount;
import com.example.ecommerce.entities.ProductInventory;

public class ProductDetailsDTO {
    private Integer productId;
    private ProductCategory productCategory;
    private String productName;
    private String productDescription;
    private double productPrice;
    private ProductInventory productInventory;
    private ProductDiscount productDiscount;

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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
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

    public ProductDetailsDTO() {
    }

    public ProductDetailsDTO(Integer productId, ProductCategory productCategory, String productName, String productDescription, double productPrice, ProductInventory productInventory, ProductDiscount productDiscount) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productInventory = productInventory;
        this.productDiscount = productDiscount;
    }
}
