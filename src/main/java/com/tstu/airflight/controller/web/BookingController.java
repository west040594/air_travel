package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.ClientDto;
import com.tstu.airflight.dto.OrderDto;
import com.tstu.airflight.dto.forms.BookingForm;
import com.tstu.airflight.model.Order;
import com.tstu.airflight.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.neo4j.kernel.api.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @GetMapping("/cart")
    public String cartPage(Model model, HttpSession session) {
        Map<Object, Object> data  = new HashMap<>();
        model.addAttribute("data", data);
        BookingForm bookingForm = new BookingForm();
        bookingForm.setClientId(((ClientDto)session.getAttribute("client")).getId());
        model.addAttribute("bookingForm", bookingForm);
        return "cartPage";
    }

    @PostMapping("/cart")
    public String processBookingFromCart(@ModelAttribute("bookingForm") @Valid BookingForm bookingForm,
                                         BindingResult result, Model model, HttpSession session) throws EntityNotFoundException {
        if(result.hasErrors()) {
            return "cartPage";
        } else {
            OrderDto order = modelMapper.map(orderService.create(bookingForm), OrderDto.class);
            session.setAttribute("client", order.getClient());
            return "redirect:/order/" + order.getId();
        }
    }
}
