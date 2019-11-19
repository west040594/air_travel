package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.Flight;
import com.tstu.airflight.model.enums.FlightStatus;
import com.tstu.airflight.repository.FlightRepository;
import com.tstu.airflight.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {


    private final FlightRepository flightRepository;

    @Override
    public Flight create(Flight flight) {
        flight.setName(generateFlightName(flight));
        flight.setStatus(FlightStatus.WAIT);
        return flightRepository.save(flight);
    }

    @Override
    public void dropAll() {
        flightRepository.dropAll();
    }

    @Override
    public Flight findById(Long id) throws EntityNotFoundException {
        Flight flight = flightRepository.findById(id, 2)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
        flight.getSeats().sort(Comparator.comparingLong(o -> o.getSeat().getSeatNumber()));
        return flight;
    }

    @Override
    public List<Flight> findAll() {
        return (List<Flight>) flightRepository.findAll(3);
    }

    @Override
    public List<List<Flight>> findFlightPaths(String origin, String destination) {
        List<List<Flight>> flightPaths = formFlightPaths(flightRepository.findPathByCity(origin, destination));
        return getActualFlightPaths(flightPaths);
    }


    private List<List<Flight>> formFlightPaths(Iterable<Map<String, List<Flight>>> path) {
        List<List<Flight>> flightPaths = new ArrayList<>();
        for (Map<String, List<Flight>> flightPathMap : path) {
            Set<Long> flightPathIds = flightPathMap.get("flightPaths").stream().map(Flight::getId).collect(Collectors.toSet());
            List<Flight> flightPath = new ArrayList<>((Collection<? extends Flight>) flightRepository.findAllById(flightPathIds, 2));
            flightPaths.add(flightPath);
        }
        return flightPaths;
    }

    /**
     * Фильтр авиа рейсов
     * @param flightPaths
     * @return
     */
    private List<List<Flight>> getActualFlightPaths(List<List<Flight>> flightPaths) {
        return flightPaths.stream()
                .filter(flights -> flights.stream()
                        .allMatch(flight -> flight.getStatus().equals(FlightStatus.WAIT)))
                .collect(Collectors.toList());
    }

    private String generateFlightName(Flight flight) {
        StringBuilder flightNameSB = new StringBuilder();
        flightNameSB
                .append(flight.getOrigination().getName())
                .append(" ")
                .append(flight.getDestination().getName())
                .append(" ")
                .append(flight.getFlightNumber());

        return flightNameSB.toString();
    }
}
