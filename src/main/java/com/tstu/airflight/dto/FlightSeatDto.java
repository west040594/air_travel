package com.tstu.airflight.dto;

import com.tstu.airflight.model.enums.SeatStatus;
import lombok.Data;

@Data
public class FlightSeatDto {
    private Long id;
    private AircraftSeatDto seat;
    private SeatStatus status;
    private String price;
}
