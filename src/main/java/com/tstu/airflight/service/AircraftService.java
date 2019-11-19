package com.tstu.airflight.service;

import com.tstu.airflight.model.Aircraft;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface AircraftService {
    Aircraft findById(Long id) throws EntityNotFoundException;
    List<Aircraft> findAll();
    Aircraft create(Aircraft aircraft);
}
