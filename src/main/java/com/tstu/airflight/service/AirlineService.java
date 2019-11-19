package com.tstu.airflight.service;

import com.tstu.airflight.model.Airline;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface AirlineService {
    Airline findById(Long id) throws EntityNotFoundException;
    List<Airline> findAll();
    Airline create(Airline airline);
}
