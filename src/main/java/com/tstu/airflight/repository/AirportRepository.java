package com.tstu.airflight.repository;

import com.tstu.airflight.model.Airport;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AirportRepository extends Neo4jRepository<Airport, Long> {

}
