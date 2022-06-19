package com.mag2kode.echarityspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "delivery_item")
@Getter
@Setter
public class DeliveryItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "donation_id")
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

}
