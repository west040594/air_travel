package com.tstu.airflight.dto;

import lombok.Data;

@Data
public class AirportDto {
    private Long id;
    private String name;
    private CityDto city;
}
