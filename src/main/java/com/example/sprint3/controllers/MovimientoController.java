package com.example.sprint3.controllers;

import com.example.sprint3.entities.Empresa;
import com.example.sprint3.entities.Movimiento;
import com.example.sprint3.services.MovimientoService;
import com.example.sprint3.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    // get all method
    @RequestMapping("enterprises/{enterpriseId}/movements")
    public ArrayList<Movimiento> getAllMovimientos(){
        return movimientoService.getAllMovimientos();
    }

    // get one method
    @RequestMapping("enterprises/{enterpriseId}/movements/{id}")
    public Movimiento getMovimiento(@PathVariable int id){
        return movimientoService.getMovimiento(id);
    }

    @PostMapping("enterprises/{enterpriseId}/movements")
    public Response postMovimiento(@RequestBody Movimiento movimiento){
        return movimientoService.postMovimiento(movimiento);
    }

    @DeleteMapping("enterprises/{enterpriseId}/movements/{id}")
    public Response deleteMovimiento(@PathVariable int id){

        return movimientoService.deleteMovimiento(id);
    }

    @PatchMapping(value = "/enterprises/{enterpriseId}/movements/update/{id}")
    public Movimiento updatedMovimientoWithMap(@PathVariable("id") int id, @RequestBody Map<Object, Object> objectMap){
        return movimientoService.updatedMovimientoWithMap(id, objectMap);
    }









}
