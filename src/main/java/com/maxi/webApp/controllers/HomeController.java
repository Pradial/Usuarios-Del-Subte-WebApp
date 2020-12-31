package com.maxi.webApp.controllers;

import com.maxi.webApp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    ObtainDatos obtainDatos;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("jsonA", obtainDatos.getDatosPorLinea("A"));
        model.addAttribute("jsonB", obtainDatos.getDatosPorLinea("B"));
        model.addAttribute("jsonC", obtainDatos.getDatosPorLinea("C"));
        model.addAttribute("jsonD", obtainDatos.getDatosPorLinea("D"));
        model.addAttribute("jsonE", obtainDatos.getDatosPorLinea("E"));
        model.addAttribute("jsonH", obtainDatos.getDatosPorLinea("H"));
        return "home";
    }
}
