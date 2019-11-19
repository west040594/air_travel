package com.tstu.airflight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Relationship(type = "HAS_BOOKING")
    private List<Booking> bookings;

    @Relationship(type = "HAS_CLIENT")
    private Client client;

    private BigDecimal price;

    private String clientData;
}
