package com.tstu.airflight.converters;

import com.tstu.airflight.dto.cart.CartAddItem;
import com.tstu.airflight.dto.forms.AirportForm;
import com.tstu.airflight.dto.forms.CityForm;
import com.tstu.airflight.dto.forms.FlightForm;
import com.tstu.airflight.model.*;
import com.tstu.airflight.model.enums.SeatStatus;
import com.tstu.airflight.model.enums.TravelClass;
import com.tstu.airflight.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AirFlightConverters {

    private final CountryService countryService;
    private final CityService cityService;
    private final AirlineService airlineService;
    private final AircraftService aircraftService;
    private final AirportService airportService;
    private final FlightService flightService;
    private final FlightSeatService flightSeatService;

    public Converter<Long, Country> longCountryConverter() {
        return context -> {
            Country country = null;
            try {
                country = countryService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return country;
        };
    }

    public Converter<Long, City> longCityConverter() {
        return context -> {
            City city = null;
            try {
                city = cityService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return city;
        };
    }

    public Converter<Long, Airline> longAirlineConverter() {
        return context -> {
            Airline airline = null;
            try {
                airline = airlineService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return airline;
        };
    }

    public Converter<Long, Aircraft> longAircraftConverter() {
        return context -> {
            Aircraft aircraft = null;
            try {
                aircraft = aircraftService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return aircraft;
        };
    }

    public Converter<Long, Airport> longAirportConverter() {
        return context -> {
            Airport airport = null;
            try {
                airport = airportService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return airport;
        };
    }

    public Converter<Long, Flight> longFlightConverter() {
        return context -> {
            Flight flight = null;
            try {
                flight = flightService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return flight;
        };
    }

    public Converter<Long, FlightSeat> longFlightSeatConverter() {
        return context -> {
            FlightSeat flightSeat = null;
            try {
                flightSeat = flightSeatService.findById(context.getSource());
            } catch (EntityNotFoundException e) {
            }
            return flightSeat;
        };
    }


    public Converter<String, LocalDateTime> stringLocalDateTimeConverter() {
        return context -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(context.getSource(), formatter);
            return localDateTime;
        };
    }

    public PropertyMap<CityForm, City> cityFormCityPropertyMap() {
        return new PropertyMap<CityForm, City>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                using(longCountryConverter()).map(source.getCountryId()).setCountry(null);
            }
        };
    }

    public PropertyMap<AirportForm, Airport> airportFormAirportPropertyMap() {
        return new PropertyMap<AirportForm, Airport>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                using(longCityConverter()).map(source.getCityId()).setCity(null);
            }
        };
    }

    public PropertyMap<FlightForm, Flight> flightFormFlightPropertyMap() {
        return new PropertyMap<FlightForm, Flight>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                using(longAircraftConverter()).map(source.getAircraftId()).setAircraft(null);
                using(longAirportConverter()).map(source.getOriginationId()).setOrigination(null);
                using(longAirportConverter()).map(source.getDestinationId()).setDestination(null);
                using(longAirlineConverter()).map(source.getAirlineId()).setAirline(null);

                using((Converter<FlightForm, List<FlightSeat>>) context -> context.getSource() == null ? null
                        : generateFlightSeats(context.getSource().getAircraftId(),
                                            context.getSource().getEconomyClassPrice(),
                                            context.getSource().getBusinessClassPrice())).map(source).setSeats(null);

                using(stringLocalDateTimeConverter()).map(source.getArrivalTime()).setArrivalTime(null);
                using(stringLocalDateTimeConverter()).map(source.getDepartureTime()).setDepartureTime(null);
            }
        };
    }

    public PropertyMap<CartAddItem, Booking> addItemBookingPropertyMap() {
        return new PropertyMap<CartAddItem, Booking>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                using(longFlightConverter()).map(source.getFlightId()).setFlight(null);
                using(longFlightSeatConverter()).map(source.getSeatId()).setSeat(null);
            }
        };
    }


    private List<FlightSeat> generateFlightSeats(Long aircraftId, String economyClassPrice, String businessClassPrice) {
        List<FlightSeat> flightSeats = new ArrayList<>();
        Aircraft aircraft = null;
        try {
            aircraft = aircraftService.findById(aircraftId);
        } catch (EntityNotFoundException e) {
            return flightSeats;
        }
        for (AircraftSeat seat : aircraft.getSeats()) {
            if(seat.getTravelClass().equals(TravelClass.BUSINESS)) {
                flightSeats.add(new FlightSeat(seat, SeatStatus.OPEN, new BigDecimal(businessClassPrice)));
            } else {
                flightSeats.add(new FlightSeat(seat, SeatStatus.OPEN, new BigDecimal(economyClassPrice)));
            }
        }
        return flightSeats;
    }
}
