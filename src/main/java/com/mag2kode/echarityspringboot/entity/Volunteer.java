package com.mag2kode.echarityspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "volunteers")
@Getter
@Setter
public class Volunteer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "comment")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteer")
    private Set<Delivery> deliverySet = new HashSet<>();

    public void add(Delivery delivery) {
        if (delivery != null) {
            if (deliverySet == null) {
                deliverySet = new HashSet<>();
            }
            deliverySet.add(delivery);
            delivery.setVolunteer(this);
        }
    }
}
