package com.example.sprint3.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController{

    @GetMapping("inicio")
    public String inicio(Model data){
        // Llama el método de seguridad que se crear en el basecontroller
        seguridad();
        // Esto toma un usuario de login y permite acceder a sus atributos desde el HTML con thymeleaf
        data.addAttribute("usuarioautenticado", seguridad());
        return "home/inicio";
    }

}
