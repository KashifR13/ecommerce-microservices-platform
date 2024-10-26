package com.ecommerce.platform.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private Date paymentDate;
    private String status;
    private Double amount;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}