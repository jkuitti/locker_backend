package org.example.lockerapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/lockers")
public class LockerController {

    @GetMapping
    public String index(){
        return "Hello there";
    }
}
