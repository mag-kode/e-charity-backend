package com.mag2kode.echarityspringboot.dto;

import com.mag2kode.echarityspringboot.entity.*;
import lombok.Data;

import java.util.Set;

@Data
public class Package{

    private Delivery delivery;
    private Address address;
    private Receiver receiver;
    private Volunteer volunteer;
    private Set<DeliveryItem> deliveryItems;

}
