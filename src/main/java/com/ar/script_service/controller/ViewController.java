package com.ar.script_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RequestMapping("/")
public class ViewController {
    @GetMapping("/")
    public String index(Model model) {
        // return "index";
        return "runscript";
    }
    
    @GetMapping("/runscript")
    public String runscript(Model model) {
        return "runscript";
    }

}
