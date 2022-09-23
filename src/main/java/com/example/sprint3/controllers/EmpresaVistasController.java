package com.example.sprint3.controllers;

import com.example.sprint3.dto.empresaDTO;
import com.example.sprint3.entities.Empresa;
import com.example.sprint3.services.EmpresaService;
import com.example.sprint3.services.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class EmpresaVistasController extends BaseController{

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("enterprises")
    public String enterprise(Model enterprises){

        // Consultar empresas en la base de datos por medio de la lógica de negocios
        ArrayList<Empresa> empresasDB = empresaService.getAllEmpresas();
        // Se arma modelo que se le pasa a thymeleaf
        enterprises.addAttribute("misempresas", empresasDB);
        return "empresas/enterprises";
    }

    @GetMapping("createenterprise")
    public String createEnterprise(){
        return "empresas/createEnterprise";
    }

    @GetMapping("errorEmpresa")
    public String error(){
        return "empresas/error";
    }

    @PostMapping("postEnterprise")
    // get the empresaDTO into the post mapping
    public RedirectView postEnterprise(empresaDTO data){

        // Create the type empresa
        Empresa empresa = new Empresa();
        // do the mapping
        empresa.setNombre(data.getNombre());
        empresa.setDireccion(data.getDireccion());
        empresa.setNIT(data.getNIT());
        empresa.setTelefono(data.getTelefono());

        Response response = empresaService.postEmpresa(empresa);
        if(response.getCode() == 200){
            return new RedirectView("enterprises");
        }
        return new RedirectView("errorEmpresa");
    }

    @GetMapping("deletempresa/{id}")
    public RedirectView deleteEmpresa(@PathVariable int id){

        Response response = empresaService.deleteEmpresa(id);

        if(response.getCode()==200){
            return new RedirectView("/enterprises");
        }
        return new RedirectView("errorEmpresa");

    }

//    @PostMapping("editempresapost")
//    public RedirectView editempresapost(UpdateEmpresaDTO data){
//        Empresa empresa = new Empresa();
//        //mapear segun los atributos
//        return new RedirectView("/admin/usuarios");
//    }


}
