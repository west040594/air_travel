package com.tstu.airflight.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping(value = {"/home", "/"})
    public String homePage(Model model) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("greentings", "Курсовая работа ПИН 1606 Штылёв Семён Николаевич");
        model.addAttribute("data", data);
        return "homePage";
    }

}
