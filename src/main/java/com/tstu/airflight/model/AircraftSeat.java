package com.tstu.airflight.model;

import com.tstu.airflight.model.enums.TravelClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AircraftSeat {
    @Id
    @GeneratedValue
    private Long id;
    private Long seatNumber;
    private TravelClass travelClass;

    public AircraftSeat(Long seatNumber, TravelClass travelClass) {
        this.seatNumber = seatNumber;
        this.travelClass = travelClass;
    }
}
