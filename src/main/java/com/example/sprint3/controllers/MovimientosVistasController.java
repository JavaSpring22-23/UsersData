package com.example.sprint3.controllers;

import com.example.sprint3.dto.movimientoDTO;
import com.example.sprint3.entities.Movimiento;
import com.example.sprint3.entities.Usuario;
import com.example.sprint3.services.MovimientoService;
import com.example.sprint3.services.Response;
import com.example.sprint3.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class MovimientosVistasController extends BaseController{

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("movements")
    public String movements(Model movimientos){

        // Consultar movimientos en la base da datos por parte del servicio
        ArrayList<Movimiento> movimientosDB = movimientoService.getAllMovimientos();
        // Armar modelo que se le pasa a thymeleaf
        movimientos.addAttribute("mismovimientos", movimientosDB);
        return "movimientos/movements";
    }

    // Crear el movimiento
    @GetMapping("createmovement")
    public String createMovement(Model model){

        // crear instancia de movimiento
        Movimiento movimiento = new Movimiento();

        ArrayList<Usuario> listUsuarios = usuarioService.getAllUsuarios();


        // añadir atributos del modelo
        model.addAttribute("titulo", "Nuevo movimiento de dinero");
        model.addAttribute("movimiento", movimiento);
        model.addAttribute("usuarios", listUsuarios);
        return "movimientos/createMovement";
    }

    @GetMapping("errorMovimiento")
    public String error(){
        return "error";
    }

    @PostMapping("postMovement")
    public RedirectView postMovement(movimientoDTO data){

        Movimiento movimiento = new Movimiento();

        movimiento.setUsuario(data.getUsuario());
        movimiento.setMonto(data.getMonto());
        movimiento.setConcepto(data.getConcepto());
        movimiento.setFecha(data.getFecha());

        Response response = movimientoService.postMovimiento(movimiento);
        if(response.getCode()==200){
            return new RedirectView("movements");
        }
        return new RedirectView("errorMovimiento");

    }

    @GetMapping("/deletemovimiento/{id}")
    public RedirectView deleteMovimiento(@PathVariable int id){

        Response response = movimientoService.deleteMovimiento(id);

        if(response.getCode()==200){
            return new RedirectView("/movements");
        }
        return new RedirectView("errorMovimiento");

    }





}
