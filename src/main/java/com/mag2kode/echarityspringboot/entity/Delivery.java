package com.mag2kode.echarityspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
public class Delivery{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "delivery_tracking_number")
    private String deliveryTrackNum;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "address_id")
    private Long address_id;

    @Column(name = "receiver_id")
    private Long receiver_id;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Receiver receiver;

    @Column(name = "volunteer_id")
    private Long volunteer_id;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "status")
    private String status;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @Column(name = "time_get")
    private String timeGet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery")
    private Set<DeliveryItem> deliveryItemSet = new HashSet<>();

    public void add(DeliveryItem item) {
        if (item != null) {
            if (deliveryItemSet == null) {
                deliveryItemSet = new HashSet<DeliveryItem>();
            }
            deliveryItemSet.add(item);
            item.setDelivery(this);
        }
    }



}
