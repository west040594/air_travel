package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.City;
import com.tstu.airflight.repository.CityRepository;
import com.tstu.airflight.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public City findById(Long id) throws EntityNotFoundException {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<City> findAll() {
        return (List<City>) cityRepository.findAll(1);
    }

    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }
}
