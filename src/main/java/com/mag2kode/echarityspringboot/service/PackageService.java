package com.mag2kode.echarityspringboot.service;

import com.mag2kode.echarityspringboot.dto.Package;
import com.mag2kode.echarityspringboot.dto.PackageResponse;

public interface PackageService{
    PackageResponse placeDelivery(Package pack);
}
