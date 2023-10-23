package com.rogeriogregorio.bookmanagementsystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/page")
public class BookPageController {
    // ... outros métodos ...

    @GetMapping(value = "/index")
    public String indexView() {
        return "index";
    }
}