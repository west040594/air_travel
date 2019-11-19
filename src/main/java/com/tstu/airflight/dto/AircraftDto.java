package com.tstu.airflight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AircraftDto {
    private Long id;
    private AircraftManufacturerDto manufacturer;
    private AircraftTypeDto type;
    private String modelNumber;
    private List<AircraftSeatDto> seats;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate manufactured;
}
