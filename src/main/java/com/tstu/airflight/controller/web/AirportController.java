package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.AirportDto;
import com.tstu.airflight.dto.CityDto;
import com.tstu.airflight.dto.forms.AirportForm;
import com.tstu.airflight.model.Airport;
import com.tstu.airflight.service.AirportService;
import com.tstu.airflight.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/airport")
public class AirportController {

    private final CityService cityService;
    private final AirportService airportService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public String airportPage(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
        Map<Object, Object> data  = new HashMap<>();
        AirportDto airport = modelMapper.map(airportService.findById(id), AirportDto.class);
        data.put("airport", airport);
        model.addAttribute("data", data);
        return "airportPage";
    }

    @GetMapping("/create")
    public String airportCreatePage(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        List<CityDto> cities = cityService.findAll().stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
        data.put("cities", cities);
        model.addAttribute("data", data);
        model.addAttribute("airportForm", new AirportForm());
        return "airportCreatePage";
    }

    @PostMapping("/create")
    public String processAirportCreate(@ModelAttribute("airportForm") @Valid AirportForm airportForm,
                                    BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "airportCreatePage";
        } else {
            Airport airport = modelMapper.map(airportForm, Airport.class);
            Long airportId = (airportService.create(airport)).getId();
            return "redirect:/airport/" + airportId;
        }
    }
}
