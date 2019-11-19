package com.tstu.airflight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tstu.airflight.model.enums.FlightStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightDto {
    private Long id;
    private String name;
    private String flightNumber;
    private AirportDto origination;
    private AirportDto destination;
    private AircraftDto aircraft;
    private AirlineDto airline;
    private FlightStatus status;
    private List<FlightSeatDto> seats;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
}
