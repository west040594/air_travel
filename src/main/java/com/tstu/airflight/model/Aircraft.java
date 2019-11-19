package com.tstu.airflight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.List;

@NodeEntity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aircraft {
    @Id
    @GeneratedValue
    private Long id;
    @Relationship(value = "HAS_MANUFACTURER")
    private AircraftManufacturer manufacturer;
    @Relationship(value = "HAS_TYPE")
    private AircraftType type;
    @Relationship(value = "HAS_SEATS")
    private List<AircraftSeat> seats;
    private String modelNumber;
    private LocalDate manufactured;
}
