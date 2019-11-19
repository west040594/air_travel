package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.Aircraft;
import com.tstu.airflight.repository.AircraftRepository;
import com.tstu.airflight.service.AircraftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    @Override
    public Aircraft findById(Long id) throws EntityNotFoundException {
        return aircraftRepository.findById(id, 2)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<Aircraft> findAll() {
        return (List<Aircraft>) aircraftRepository.findAll(2);
    }

    @Override
    public Aircraft create(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }
}
