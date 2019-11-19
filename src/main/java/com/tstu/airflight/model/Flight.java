package com.tstu.airflight.model;


import com.tstu.airflight.model.enums.FlightStatus;
import lombok.*;
import org.neo4j.ogm.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@NodeEntity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Required
    private String flightNumber;

    @Relationship(type = "HAS_FLIGHT", direction = Relationship.INCOMING)
    private Airport origination;

    @Relationship(type = "FLYING_TO")
    private Airport destination;

    @Relationship(value = "HAS_AIRCRAFT")
    private Aircraft aircraft;

    @Relationship(value = "HAS_AIRLINE")
    private Airline airline;

    @Relationship(value = "HAS_FLIGHT_SEAT")
    private List<FlightSeat> seats;

    private FlightStatus status;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
}
