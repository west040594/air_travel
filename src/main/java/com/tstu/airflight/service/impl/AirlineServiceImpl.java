package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.Airline;
import com.tstu.airflight.repository.AirlineRepository;
import com.tstu.airflight.service.AirlineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public Airline findById(Long id) throws EntityNotFoundException {
        return airlineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<Airline> findAll() {
        return (List<Airline>) airlineRepository.findAll();
    }

    @Override
    public Airline create(Airline airline) {
        return airlineRepository.save(airline);
    }
}
