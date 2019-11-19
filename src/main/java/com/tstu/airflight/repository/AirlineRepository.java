package com.tstu.airflight.repository;

import com.tstu.airflight.model.Airline;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AirlineRepository extends Neo4jRepository<Airline, Long> {
}
