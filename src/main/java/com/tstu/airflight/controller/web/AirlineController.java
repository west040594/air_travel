package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.AirlineDto;
import com.tstu.airflight.dto.AirportDto;
import com.tstu.airflight.dto.CityDto;
import com.tstu.airflight.dto.forms.AirlineForm;
import com.tstu.airflight.dto.forms.AirportForm;
import com.tstu.airflight.model.Airline;
import com.tstu.airflight.model.Airport;
import com.tstu.airflight.service.AirlineService;
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
@RequestMapping("/airline")
public class AirlineController {

    private final AirlineService airlineService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public String airportPage(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
        Map<Object, Object> data  = new HashMap<>();
        AirlineDto airline = modelMapper.map(airlineService.findById(id), AirlineDto.class);
        data.put("airline", airline);
        model.addAttribute("data", data);
        return "airlinePage";
    }

    @GetMapping("/create")
    public String airlineCreatePage(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("airlineForm", new AirlineForm());
        return "airlineCreatePage";
    }

    @PostMapping("/create")
    public String processAirportCreate(@ModelAttribute("airlineForm") @Valid AirlineForm airlineForm,
                                    BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "airlineCreatePage";
        } else {
            Airline airline = modelMapper.map(airlineForm, Airline.class);
            Long airportId = (airlineService.create(airline)).getId();
            return "redirect:/airline/" + airportId;
        }
    }
}
