package com.tstu.airflight.dto.forms;

import lombok.Data;

@Data
public class FlightForm {
    private String flightNumber;
    private Long originationId;
    private Long destinationId;
    private Long aircraftId;
    private Long airlineId;
    private String departureTime;
    private String arrivalTime;
    private String economyClassPrice;
    private String businessClassPrice;
}
