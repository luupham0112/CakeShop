package com.phanmem.cakeshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    private String firstName;

    private String lastName;
    private String username;
    private String country;

    private String phoneNumber;
    private String address;

    private String password;
    @Lob
    @Column( columnDefinition = "MEDIUMBLOB")
    private String image;

    private String city;

    @OneToOne(mappedBy = "customer")
    private Cart cart;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable( name = "customers_roles",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Collection<Role> roles;
}
