package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.Airport;
import com.tstu.airflight.repository.AirportRepository;
import com.tstu.airflight.service.AirportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {
    private final AirportRepository airportRepository;

    public Airport create(Airport airport) {
        Airport save = airportRepository.save(airport);
        return save;
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public Airport findById(Long id) throws EntityNotFoundException {
        return airportRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<Airport> findAll() {
        return (List<Airport>) airportRepository.findAll();
    }
}
