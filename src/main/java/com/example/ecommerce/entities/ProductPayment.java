package com.example.ecommerce.entities;

import javax.persistence.*;

@Entity
@Table(name="payment")
public class ProductPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="payment_id")
    private Integer paymentId;

    @Column(name="payment_type")
    private String paymentType;

    @Column(name="payment_amount",precision = 10,scale = 2)
    private Double paymentAmount;

    @Column(name="payment_status")
    private String paymentStatus;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ProductPayment() {
    }

    public ProductPayment(Integer paymentId, String paymentType, Double paymentAmount, String paymentStatus) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.paymentAmount = paymentAmount;
        this.paymentStatus = paymentStatus;
    }
}
