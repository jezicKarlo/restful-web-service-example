package hr.fer.rznu.restexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlaController {

    @GetMapping
    public String hello() {
        return "Hello Karlo";
    }
}
