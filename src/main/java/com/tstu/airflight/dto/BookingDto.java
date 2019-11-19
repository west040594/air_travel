package com.tstu.airflight.dto;

import lombok.Data;

@Data
public class BookingDto {
    private Long id;
    private FlightSeatDto seat;
    private FlightDto flight;
}
