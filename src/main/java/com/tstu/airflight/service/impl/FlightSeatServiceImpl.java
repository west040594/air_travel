package com.tstu.airflight.service.impl;

import com.tstu.airflight.model.FlightSeat;
import com.tstu.airflight.model.enums.SeatStatus;
import com.tstu.airflight.repository.FlightSeatRepository;
import com.tstu.airflight.service.FlightSeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.neo4j.storageengine.api.EntityType;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;

    @Override
    public FlightSeat findById(Long id) throws EntityNotFoundException {
        return flightSeatRepository.findById(id,2)
                .orElseThrow(() -> new EntityNotFoundException(EntityType.NODE, id));
    }

    @Override
    public FlightSeat changeStatus(FlightSeat flightSeat, SeatStatus seatStatus) {
        flightSeat.setStatus(seatStatus);
        return flightSeatRepository.save(flightSeat, 2);
    }
}
