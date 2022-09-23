package com.example.sprint3.controllers;

import com.example.sprint3.entities.Login;
import com.example.sprint3.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {

    //con el autowired protected ya se realiza la inyeccion en todas las clases hijas del base controller
    @Autowired
    protected LoginService loginService;

    protected Login seguridad(){
        //ingresa a la información de spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Tomo el correo electronico que nos guardó spring security
        String currentPrincipalName = authentication.getName();
        return loginService.selectByUsername(currentPrincipalName);
    }
}
