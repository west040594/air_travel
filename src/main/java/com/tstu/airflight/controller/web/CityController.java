package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.CityDto;
import com.tstu.airflight.dto.CountryDto;
import com.tstu.airflight.dto.forms.CityForm;
import com.tstu.airflight.model.City;
import com.tstu.airflight.service.CityService;
import com.tstu.airflight.service.CountryService;
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

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public String cityPage(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
        Map<Object, Object> data  = new HashMap<>();
        CityDto city = modelMapper.map(cityService.findById(id), CityDto.class);
        data.put("city", city);
        model.addAttribute("data", data);
        return "cityPage";
    }

    @GetMapping("/create")
    public String cityCreatePage(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        List<CountryDto> countries = countryService.findAll().stream()
                .map(country -> modelMapper.map(country, CountryDto.class))
                .collect(Collectors.toList());
        data.put("countries", countries);
        model.addAttribute("data", data);
        model.addAttribute("cityForm", new CityForm());
        return "cityCreatePage";
    }

    @PostMapping("/create")
    public String processCityCreate(@ModelAttribute("cityForm") @Valid CityForm cityForm,
                                    BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "cityCreatePage";
        } else {
            City city = modelMapper.map(cityForm, City.class);
            Long cityId = (cityService.create(city)).getId();
            return "redirect:/city/" + cityId;
        }
    }
}
