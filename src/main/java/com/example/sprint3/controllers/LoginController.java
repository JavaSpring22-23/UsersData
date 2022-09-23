package com.example.sprint3.controllers;


import com.example.sprint3.dto.loginDTO;
import com.example.sprint3.dto.registroDTO;
import com.example.sprint3.entities.Login;
import com.example.sprint3.services.LoginService;
import com.example.sprint3.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String getLogin(){
        return "/login/login";
    }

    @GetMapping("/register")
    public String registro(){
        return "login/registro";
    }

    @PostMapping("/postLogin")
    public RedirectView postLogin(loginDTO data){

        if(data.getPassword() == null || data.getPassword().equals("")){
            return new RedirectView("errorLogin");
        }

        Login user = new Login();

        user.setCorreoElectronico(data.getCorreoElectronico());
        user.setPassword(data.getPassword());

        Response response = loginService.loginUser(user);

        if(response.getCode() == 200){
            return new RedirectView("inicio");
        }
        return new RedirectView("errorLogin");
    }

    @PostMapping("/postRegistro")
    public RedirectView postRegistro(registroDTO data){
        if(data.getPassword() == null || data.getPassword().equals("")){
            return new RedirectView("errorLogin");
        }

        if(!data.getPassword().equals(data.getValidaPassword())){
            System.out.println("Las contraseñas no coinciden");
            return new RedirectView("errorLogin");
        }

        Login user = new Login();

        // mapping

        user.setNombre(data.getNombre());
        user.setApellido(data.getApellido());
        user.setCorreoElectronico(data.getCorreoElectronico());
        user.setPassword(data.getPassword());

        Response response = loginService.createLoginUsuario(user);
        if(response.getCode() == 200){
            return new RedirectView("/inicio");
        }
        return new RedirectView("error");
    }

    @GetMapping("errorLogin")
    public String error(){
        return "/error";
    }



}
