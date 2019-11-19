package com.tstu.airflight.dto;

import lombok.Data;

@Data
public class CityDto {
    private Long id;
    private String name;
    private String code;
    private CountryDto country;
}
