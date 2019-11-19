package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.CountryDto;
import com.tstu.airflight.dto.forms.CountryForm;
import com.tstu.airflight.model.Country;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/country")
@Slf4j
public class CountryController {

    private final CountryService countryService;
    private final ModelMapper modelMapper;


    @GetMapping("/{id}")
    public String flightPage(@PathVariable Long id, Model model) throws EntityNotFoundException {
        HashMap<Object, Object> data = new HashMap<>();
        CountryDto country = modelMapper.map(countryService.findById(id), CountryDto.class);
        data.put("country",  country);
        model.addAttribute("data", data);
        return "countryPage";
    }

    @GetMapping("/create")
    public String createCountryForm(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        model.addAttribute("data", data);
        model.addAttribute("countryForm", new CountryForm());
        return "countryCreatePage";
    }

    @PostMapping("/create")
    public String processCreateCountry(@ModelAttribute("countryForm") @Valid CountryForm countryForm,
                                       BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "countryCreatePage";
        } else {
            Long countryId = (countryService.create(modelMapper.map(countryForm, Country.class))).getId();
            return "redirect:/country/" + countryId;
        }
    }
}
