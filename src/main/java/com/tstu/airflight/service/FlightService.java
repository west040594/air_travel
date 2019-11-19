package com.tstu.airflight.service;

import com.tstu.airflight.model.City;
import com.tstu.airflight.model.Flight;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface FlightService {
    Flight create(Flight flight);
    void dropAll();
    List<Flight> findAll();
    Flight findById(Long id) throws EntityNotFoundException;
    List<List<Flight>> findFlightPaths(String origin, String destination);
}
