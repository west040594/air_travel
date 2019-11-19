package com.tstu.airflight.repository;

import com.tstu.airflight.model.Aircraft;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AircraftRepository extends Neo4jRepository<Aircraft, Long> {
}
