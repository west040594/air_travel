package com.tstu.airflight.repository;

import com.tstu.airflight.model.FlightSeat;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface FlightSeatRepository extends Neo4jRepository<FlightSeat, Long> {

}
