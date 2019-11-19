package com.tstu.airflight.repository;


import com.tstu.airflight.model.Flight;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FlightRepository extends Neo4jRepository<Flight, Long> {

    @Query("MATCH p=(:City{name:{origin}})-[:HAS_AIRPORT]->(:Airport)-[:FLYING_TO|:HAS_FLIGHT*..50]->(:Airport)<-[:HAS_AIRPORT]-(:City{name:{destination}}) WITH NODES(p) as nodes WITH FILTER(node in nodes WHERE (node:Flight)) as flightPaths RETURN flightPaths")
    Iterable<Map<String, List<Flight>>> findPathByCity(@Param("origin") String origin, @Param("destination") String destination);

    @Query("MATCH(n) DETACH DELETE n")
    public void dropAll();
}


