package com.tstu.airflight.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String passport;
    private String balance;
}
