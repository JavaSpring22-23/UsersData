package com.example.sprint3.controllers;


import com.example.sprint3.entities.Empresa;
import com.example.sprint3.services.EmpresaService;
import com.example.sprint3.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Get all metodo
    @RequestMapping("/enterprises")
    public ArrayList<Empresa> getAllEmpresas(){
        return empresaService.getAllEmpresas();
    }

    // Get 1 por id método
    @RequestMapping("/enterprises/{id}")
    public Empresa getEmpresa(@PathVariable int id){
        return empresaService.getEmpresa(id);
    }

    // Post método
    @PostMapping("/enterprises")
    public Response postEmpresa(@RequestBody Empresa empresa){
        return empresaService.postEmpresa(empresa);
    }

    // Delete método
    @DeleteMapping("/enterprises/{id}")
    public Response deleteEmpresa(@PathVariable int id){
        return empresaService.deleteEmpresa(id);
    }

    // Patch método
    @PatchMapping(value = "/enterprises/update/{id}")
    public Empresa updatedEmpresaWithMap(@PathVariable("id") int id, @RequestBody Map<Object, Object> objectMap){
        return empresaService.updatedEmpresaWithMap(id, objectMap);
    }


}
