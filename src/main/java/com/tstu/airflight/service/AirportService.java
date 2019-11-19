package com.tstu.airflight.service;

import com.tstu.airflight.model.Airport;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface AirportService {
    Airport findById(Long id) throws EntityNotFoundException;
    List<Airport> findAll();
    Airport create(Airport airport);
    void deleteById(Long id);
}
