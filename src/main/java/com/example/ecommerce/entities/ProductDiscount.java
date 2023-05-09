package com.example.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name="discount")
public class ProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="discount_id")
    private Integer discountId ;

    @Column(name="discount_name")
    private String discountName;

    @Column(name="discount_description")
    private String discountDescription;

    @Column(name="discount_percentage")
    private Integer discountPercentage;

    @Column(name="active")
    private String active;

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public ProductDiscount() {
    }

    public ProductDiscount(Integer discountId, String discountName, String discountDescription, Integer discountPercentage, String active) {
        this.discountId = discountId;
        this.discountName = discountName;
        this.discountDescription = discountDescription;
        this.discountPercentage = discountPercentage;
        this.active = active;
    }


}
