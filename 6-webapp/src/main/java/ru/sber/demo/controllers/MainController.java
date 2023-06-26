package ru.sber.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class MainController {
    //http://localhost:8080/authorization?username=Tom
    @RequestMapping("/authorization")
    public String home(
            @RequestParam(value = "username", required = true) String username,
            Model page) {
        page.addAttribute("username", username);
        return "authorization.html";
    }
}
