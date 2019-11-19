package com.tstu.airflight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String code;

    @Relationship(type = "HAS_COUNTRY")
    private Country country;


    public City(String name, String code, Country country) {
        this.name = name;
        this.code = code;
        this.country = country;
    }
}
