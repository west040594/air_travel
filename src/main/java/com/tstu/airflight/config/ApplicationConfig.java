package com.tstu.airflight.config;

import com.tstu.airflight.converters.AirFlightConverters;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper(AirFlightConverters converters) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(converters.cityFormCityPropertyMap());
        modelMapper.addMappings(converters.airportFormAirportPropertyMap());
        modelMapper.addMappings(converters.flightFormFlightPropertyMap());
        modelMapper.addMappings(converters.addItemBookingPropertyMap());
        return modelMapper;
    }
}
