package com.mag2kode.echarityspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name="donation")
@Data
public class Donation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private DonationCategory categoryId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="size")
    private double size;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="active")
    private boolean active;

    @Column(name="date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    public String getDescription(){
        return description;
    }
}
