package com.mag2kode.echarityspringboot.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class PackageResponse{
    @NonNull
    private String deliveryTrackNum;
}
