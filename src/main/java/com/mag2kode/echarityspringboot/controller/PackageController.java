package com.mag2kode.echarityspringboot.controller;

import com.mag2kode.echarityspringboot.dto.Package;
import com.mag2kode.echarityspringboot.dto.PackageResponse;
import com.mag2kode.echarityspringboot.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
public class PackageController{
    private PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService){

        this.packageService = packageService;
    }

    @CrossOrigin("http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, path = "/api/delivery")
    public PackageResponse packageResponse(@RequestBody Package pack) {
        System.out.println("packResponse: " + pack.toString());
        PackageResponse packageResponse = packageService.placeDelivery(pack);
        return packageResponse;
    }
}
