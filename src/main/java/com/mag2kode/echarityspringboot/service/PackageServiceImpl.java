package com.mag2kode.echarityspringboot.service;

import com.mag2kode.echarityspringboot.dao.DeliveriesRepository;
import com.mag2kode.echarityspringboot.dao.ReceiverRepository;
import com.mag2kode.echarityspringboot.dto.Package;
import com.mag2kode.echarityspringboot.dto.PackageResponse;
import com.mag2kode.echarityspringboot.entity.Delivery;
import com.mag2kode.echarityspringboot.entity.DeliveryItem;
import com.mag2kode.echarityspringboot.entity.Receiver;
import com.mag2kode.echarityspringboot.entity.Volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class PackageServiceImpl implements PackageService {

    private DeliveriesRepository deliveriesRepository;

    @Autowired
    public PackageServiceImpl(DeliveriesRepository deliveriesRepository){
        this.deliveriesRepository = deliveriesRepository;
    }

    @Override
    @Transactional
    public PackageResponse placeDelivery(Package pack){

        System.out.println("saving delivery: " + pack.toString());

        //retrieve dto
        Delivery delivery = pack.getDelivery();

        //generate tracking num
        String deliveryTrackNum = generateDeliveryTrackNumber();
        delivery.setDeliveryTrackNum(deliveryTrackNum);

        //populate delivery items
        Set<DeliveryItem> deliveryItemSet = pack.getDeliveryItems();
        deliveryItemSet.forEach(i -> delivery.add(i));

        //add address
        delivery.setAddress(pack.getAddress());

        //add receiver
        Receiver receiver = pack.getReceiver();
        receiver.add(delivery);

        //add volunteer
        Volunteer volunteer = pack.getVolunteer();
        volunteer.add(delivery);

        deliveriesRepository.save(delivery);


        //return response

        return new PackageResponse(deliveryTrackNum);
    }

    private String generateDeliveryTrackNumber(){
        //generate random Unique ID (version 4)
        return UUID.randomUUID().toString();

    }
}
