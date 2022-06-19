package com.mag2kode.echarityspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "receivers")
@Getter
@Setter
public class Receiver{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private String age;

    @Column(name = "comment")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private Set<Delivery> deliverySet = new HashSet<>();

    public void add(Delivery delivery) {
        if (delivery != null) {
            if (deliverySet == null) {
                deliverySet = new HashSet<>();
            }
            deliverySet.add(delivery);
            delivery.setReceiver(this);
        }
    }
}