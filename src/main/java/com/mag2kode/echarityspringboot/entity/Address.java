package com.mag2kode.echarityspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "city")
    private String street;

    @Column(name = "city")
    private String building;

    @Column(name = "city")
    private String tap;

    @Column(name = "city")
    private String apartment;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Delivery delivery;

}
