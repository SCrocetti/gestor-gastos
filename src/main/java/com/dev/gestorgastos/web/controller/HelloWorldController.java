package com.dev.gestorgastos.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/saludos")
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello() {
        return "salutations/Hello"; // Note the path to the HTML file within the subdirectory
    }
}