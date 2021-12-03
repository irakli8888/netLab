package ru.netcracker.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author IrklKvch
 */
@RestController
public class StartController {


    @GetMapping
    public String test(){
        return "test";
    }
}
