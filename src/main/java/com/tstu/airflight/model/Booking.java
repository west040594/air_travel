package com.tstu.airflight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_SEAT")
    private FlightSeat seat;

    @Relationship(type = "HAS_FLIGHT")
    private Flight flight;
}
