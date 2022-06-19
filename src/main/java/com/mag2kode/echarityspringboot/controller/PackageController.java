package com.mag2kode.echarityspringboot.controller;

import com.mag2kode.echarityspringboot.dto.Package;
import com.mag2kode.echarityspringboot.dto.PackageResponse;
import com.mag2kode.echarityspringboot.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/submit-deliver")
public class PackageController{
    private PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService){
        this.packageService = packageService;
    }

    @PostMapping("/pack")
    public PackageResponse packResponse(@RequestBody Package pack) {
        PackageResponse packageResponse = packageService.placeDelivery(pack);
        return packageResponse;
    }
}
