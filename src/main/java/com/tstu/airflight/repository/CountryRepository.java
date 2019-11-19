package com.tstu.airflight.repository;

import com.tstu.airflight.model.Country;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CountryRepository extends Neo4jRepository<Country, Long> {
}
