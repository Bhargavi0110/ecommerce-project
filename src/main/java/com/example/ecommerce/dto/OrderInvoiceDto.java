package com.example.ecommerce.dto;

public class OrderInvoiceDto {

    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImages;
    private Integer productQuantity;

    private Double priceAfterDiscount;

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


    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(Double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public OrderInvoiceDto() {
    }

    public OrderInvoiceDto(String productName, String productDescription, Double productPrice, String productImages, Integer productQuantity, Double priceAfterDiscount) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productImages = productImages;
        this.productQuantity = productQuantity;
        this.priceAfterDiscount = priceAfterDiscount;
    }
}
