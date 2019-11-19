package com.tstu.airflight.repository;

import com.tstu.airflight.model.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CityRepository extends Neo4jRepository<City, Long> {

}
