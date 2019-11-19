package com.tstu.airflight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AircraftManufacturer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    public AircraftManufacturer(String name) {
        this.name = name;
    }
}
