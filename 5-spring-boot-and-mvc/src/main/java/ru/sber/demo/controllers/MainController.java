package ru.sber.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/authorization")
    public String home() {
        return "authorization.html";
    }
}
