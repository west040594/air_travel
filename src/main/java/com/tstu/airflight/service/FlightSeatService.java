package com.tstu.airflight.service;

import com.tstu.airflight.model.FlightSeat;
import com.tstu.airflight.model.enums.SeatStatus;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;

public interface FlightSeatService {
    FlightSeat findById(Long id) throws EntityNotFoundException;
    FlightSeat changeStatus(FlightSeat flightSeat, SeatStatus seatStatus);
}
