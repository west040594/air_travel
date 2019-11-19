package com.tstu.airflight.model;

import com.tstu.airflight.model.enums.SeatStatus;
import com.tstu.airflight.model.enums.TravelClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.math.BigDecimal;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSeat {
    @Id
    @GeneratedValue
    private Long id;
    private AircraftSeat seat;
    private SeatStatus status;
    private BigDecimal price;

    public FlightSeat(AircraftSeat seat, SeatStatus status, BigDecimal price) {
        this.seat = seat;
        this.status = status;
        this.price = price;
    }
}
