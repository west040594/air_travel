package com.tstu.airflight.utils;

import com.tstu.airflight.model.*;
import com.tstu.airflight.model.enums.FlightStatus;
import com.tstu.airflight.model.enums.SeatStatus;
import com.tstu.airflight.model.enums.TravelClass;
import com.tstu.airflight.service.FlightService;
import com.tstu.airflight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataBaseInit {

    private final FlightService flightService;
    private final UserService userService;

    public void init() {
        flightService.dropAll();

        Country russia = new Country("Россия", "RU");
        City moscow = new City("Москва", "MSK",  russia);
        City oren = new City("Оренбург", "ORN",  russia);
        City ufa = new City("Уфа","UFA",  russia);
        City samara = new City("Самара", "SAM",  russia);
        City vladivostok = new City("Владивосток", "VLD",  russia);
        City ekaterin = new City("Екатеринбург", "EKB",  russia);

        Airport airport = Airport.builder().name("Шереметьево").city(moscow).build();
        Airport airport2 = Airport.builder().name("Внуково").city(moscow).build();
        Airport airport3 = Airport.builder().name("Курумоч").city(samara).build();
        Airport airport4 = Airport.builder().name("Владивосток").city(vladivostok).build();
        Airport airport5 = Airport.builder().name("Уфа").city(ufa).build();
        Airport airport6 = Airport.builder().name("Оренбург").city(oren).build();
        Airport airport7 = Airport.builder().name("Кольцово").city(ekaterin).build();


        AircraftType regional = new AircraftType("REGIONAL");
        AircraftType wideBody = new AircraftType("WIDE_BODY");

        AircraftManufacturer boeing = new AircraftManufacturer("Boeing");

        Aircraft aircraft = Aircraft.builder()
                .manufactured(LocalDate.now().minusDays(720))
                .manufacturer(boeing)
                .modelNumber("a")
                .type(wideBody)
                .seats(generateAircraftSeats())
                .build();

        Aircraft aircraft2 = Aircraft.builder()
                .manufactured(LocalDate.now().minusDays(720))
                .manufacturer(boeing)
                .modelNumber("b")
                .type(wideBody)
                .seats(generateAircraftSeats())
                .build();

        Aircraft aircraft3 = Aircraft.builder()
                .manufactured(LocalDate.now().minusDays(720))
                .manufacturer(boeing)
                .modelNumber("c")
                .type(wideBody)
                .seats(generateAircraftSeats())
                .build();

        Aircraft aircraft4 = Aircraft.builder()
                .manufactured(LocalDate.now().minusDays(720))
                .manufacturer(boeing)
                .modelNumber("d")
                .type(wideBody)
                .seats(generateAircraftSeats())
                .build();

        Aircraft aircraft5 = Aircraft.builder()
                .manufactured(LocalDate.now().minusDays(720))
                .manufacturer(boeing)
                .modelNumber("e")
                .type(wideBody)
                .seats(generateAircraftSeats())
                .build();

        Aircraft aircraft6 = Aircraft.builder()
                .manufactured(LocalDate.now().minusDays(720))
                .manufacturer(boeing)
                .modelNumber("f")
                .type(wideBody)
                .seats(generateAircraftSeats())
                .build();

        Airline aeroflot = new Airline("Аэрофлот");

        Flight flight =  Flight.builder()
                .origination(airport)
                .destination(airport3)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft)
                .status(FlightStatus.WAIT)
                .flightNumber("SU1")
                .airline(aeroflot)
                .build();

        flight.setSeats(generateFlightSeats(flight.getAircraft()));


        Flight flight2 =  Flight.builder()
                .origination(airport3)
                .destination(airport4)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft2)
                .status(FlightStatus.WAIT)
                .flightNumber("SU2")
                .airline(aeroflot)
                .build();

        flight2.setSeats(generateFlightSeats(flight2.getAircraft()));

        Flight flight3 =  Flight.builder()
                .origination(airport2)
                .destination(airport7)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft3)
                .status(FlightStatus.WAIT)
                .flightNumber("SU3")
                .airline(aeroflot)
                .build();

        flight3.setSeats(generateFlightSeats(flight3.getAircraft()));

        Flight flight4 =  Flight.builder()
                .origination(airport7)
                .destination(airport6)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft4)
                .status(FlightStatus.WAIT)
                .flightNumber("SU4")
                .airline(aeroflot)
                .build();
        flight4.setSeats(generateFlightSeats(flight4.getAircraft()));

        Flight flight5 =  Flight.builder()
                .origination(airport6)
                .destination(airport5)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft5)
                .status(FlightStatus.WAIT)
                .flightNumber("SU5")
                .airline(aeroflot)
                .build();

        flight5.setSeats(generateFlightSeats(flight5.getAircraft()));

        Flight flight6 =  Flight.builder()
                .origination(airport6)
                .destination(airport4)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft6)
                .status(FlightStatus.WAIT)
                .flightNumber("SU6")
                .airline(aeroflot)
                .build();

        flight6.setSeats(generateFlightSeats(flight6.getAircraft()));

        Flight flight7 =  Flight.builder()
                .origination(airport)
                .destination(airport4)
                .arrivalTime(LocalDateTime.now())
                .departureTime(LocalDateTime.now().plus(Duration.ofDays(1)))
                .aircraft(aircraft3)
                .status(FlightStatus.WAIT)
                .flightNumber("SU17")
                .airline(aeroflot)
                .build();


        flight7.setSeats(generateFlightSeats(flight7.getAircraft()));

        Arrays.asList(flight, flight2, flight3, flight4, flight5, flight6, flight7)
                .forEach(flightService::create);


        User west223 = User.builder()
                .email("user@gmail.com")
                .username("user")
                .password("user")
                .build();
        userService.register(west223);
    }

    private List<AircraftSeat> generateAircraftSeats() {
        List<AircraftSeat> seats = new ArrayList<>();
        for (int i = 1; i <= 300 ; i++) {
            if(i > 250) {
                seats.add(new AircraftSeat((long) i, TravelClass.BUSINESS));
            } else {
                seats.add(new AircraftSeat((long) i, TravelClass.ECONOMY));
            }
        }
        return seats;
    }

    private List<FlightSeat> generateFlightSeats(Aircraft aircraft) {
        List<FlightSeat> flightSeats = new ArrayList<>();
        for (AircraftSeat seat : aircraft.getSeats()) {
            if(seat.getTravelClass().equals(TravelClass.BUSINESS)) {
                flightSeats.add(new FlightSeat(seat, SeatStatus.CLOSED, new BigDecimal(5000)));
            } else {
                flightSeats.add(new FlightSeat(seat, SeatStatus.OPEN, new BigDecimal(1000)));
            }
        }
        return flightSeats;
    }
}
