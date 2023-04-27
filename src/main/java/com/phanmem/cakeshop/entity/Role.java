package com.phanmem.cakeshop.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String name;


}
