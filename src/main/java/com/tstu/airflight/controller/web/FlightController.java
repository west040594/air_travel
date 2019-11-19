package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.*;
import com.tstu.airflight.dto.forms.FlightForm;
import com.tstu.airflight.model.Airline;
import com.tstu.airflight.model.Flight;
import com.tstu.airflight.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;
    private final CityService cityService;
    private final AirportService airportService;
    private final AirlineService airlineService;
    private final AircraftService aircraftService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String flightListPage(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        List<FlightDto> flights = flightService.findAll().stream()
                .map(chat -> modelMapper.map(chat, FlightDto.class))
                .collect(Collectors.toList());

        data.put("flights", flights);
        model.addAttribute("data", data);
        return "flightListPage";
    }


    @GetMapping("/connect")
    public String connectingFlightListPage(Model model,
                                           @RequestParam(required = false) String origin,
                                           @RequestParam(required = false) String destination) {
        HashMap<Object, Object> data = new HashMap<>();
        List<List<FlightDto>> flightPaths = flightService.findFlightPaths(origin, destination).stream()
                .map(flights ->
                        flights.stream().map(flight -> modelMapper.map(flight, FlightDto.class)).collect(Collectors.toList()))
                .collect(Collectors.toList());
        data.put("flightPaths", flightPaths);
        List<CityDto> cities = cityService.findAll().stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
        data.put("cities", cities);
        model.addAttribute("data", data);
        return "connectingFlightListPage";
    }

    @GetMapping("/{id}")
    public String flightPage(@PathVariable Long id, Model model) throws EntityNotFoundException {
        HashMap<Object, Object> data = new HashMap<>();
        FlightDto flight = modelMapper.map(flightService.findById(id), FlightDto.class);
        data.put("flight",  flight);
        model.addAttribute("data", data);
        return "flightPage";
    }

    @GetMapping("/create")
    public String flightCreateForm(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        List<AirportDto> airports = airportService.findAll().stream()
                .map(airport -> modelMapper.map(airport, AirportDto.class))
                .collect(Collectors.toList());
        data.put("airports", airports);
        List<AirlineDto> airlines = airlineService.findAll().stream()
                .map(airline -> modelMapper.map(airline, AirlineDto.class))
                .collect(Collectors.toList());
        data.put("airlines", airlines);

        List<AircraftDto> aircrafts = aircraftService.findAll().stream()
                .map(aircraft -> modelMapper.map(aircraft, AircraftDto.class))
                .collect(Collectors.toList());
        data.put("aircrafts", aircrafts);

        model.addAttribute("data", data);
        model.addAttribute("flightForm", new FlightForm());
        return "flightCreatePage";
    }

    @PostMapping("/create")
    public String processFlightCreate(@ModelAttribute("flightForm") @Valid FlightForm flightForm,
                                    BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "flightCreatePage";
        } else {
            Flight flightModel = modelMapper.map(flightForm, Flight.class);
            Long chatId = (flightService.create(flightModel)).getId();
            return "redirect:/flight/" + chatId;
        }
    }

}
