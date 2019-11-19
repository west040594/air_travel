package com.tstu.airflight.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private List<BookingDto> bookings;
    private ClientDto client;
    private String price;
    private String clientData;
}
