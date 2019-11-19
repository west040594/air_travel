package com.tstu.airflight.service;

import com.tstu.airflight.model.City;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface CityService {
    City findById(Long id) throws EntityNotFoundException;
    List<City> findAll();
    City create(City city);
}
