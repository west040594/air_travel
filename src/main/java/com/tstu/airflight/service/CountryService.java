package com.tstu.airflight.service;

import com.tstu.airflight.model.Country;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

import java.util.List;

public interface CountryService {
    Country findById(Long id) throws EntityNotFoundException;
    List<Country> findAll();
    Country create(Country country);
}
