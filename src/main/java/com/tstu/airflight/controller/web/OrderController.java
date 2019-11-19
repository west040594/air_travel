package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.OrderDto;
import com.tstu.airflight.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final ModelMapper modelMapper;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public String orderPage(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
        Map<Object, Object> data  = new HashMap<>();
        OrderDto order = modelMapper.map(orderService.findById(id), OrderDto.class);
        data.put("order", order);
        model.addAttribute("data", data);
        return "orderPage";
    }

}
