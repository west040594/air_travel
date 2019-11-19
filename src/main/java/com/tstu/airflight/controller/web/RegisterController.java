package com.tstu.airflight.controller.web;

import com.tstu.airflight.dto.forms.RegisterForm;
import com.tstu.airflight.model.User;
import com.tstu.airflight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "registrationPage";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("registerForm") @Valid RegisterForm registerForm, BindingResult result) {
        if(result.hasErrors()) {
            return "registrationPage";
        } else {

            userService.register(modelMapper.map(registerForm, User.class));
            return "redirect:/login";
        }
    }
}
