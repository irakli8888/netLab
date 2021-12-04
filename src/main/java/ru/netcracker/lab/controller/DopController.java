package ru.netcracker.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DopController {
    @GetMapping
    public String index(){
        return "index";
    }
}
