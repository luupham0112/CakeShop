package com.phanmem.cakeshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private Date orderDate;

    private double totalPrice;

    private String orderStatus;
    private String notes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
