package com.tstu.airflight.dto.cart;


import lombok.Data;

@Data
public class CartAddItem {
    private Long flightId;
    private Long seatId;
}
