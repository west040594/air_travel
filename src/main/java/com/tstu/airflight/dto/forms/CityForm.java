package com.tstu.airflight.dto.forms;

import lombok.Data;

@Data
public class CityForm {
    private String name;
    private String code;
    private Long countryId;
}
