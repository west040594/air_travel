package com.tstu.airflight.dto;

import com.tstu.airflight.model.enums.TravelClass;
import lombok.Data;

@Data
public class AircraftSeatDto {
    private Long id;
    private Long seatNumber;
    private TravelClass travelClass;
}
