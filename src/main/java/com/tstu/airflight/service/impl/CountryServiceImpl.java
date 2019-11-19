package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.Country;
import com.tstu.airflight.repository.CountryRepository;
import com.tstu.airflight.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country findById(Long id) throws EntityNotFoundException {
        return countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll(1);
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }
}
