package com.example.sprint3.controllers;

import com.example.sprint3.dto.usuarioDTO;
import com.example.sprint3.entities.Empresa;
import com.example.sprint3.entities.EnumUsuario;
import com.example.sprint3.entities.Usuario;
import com.example.sprint3.services.EmpresaService;
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
import java.util.List;

@Controller
public class UsuarioVistasController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EmpresaService empresaService;


    @GetMapping("users")
    public String users(Model usuarios){

        // Acceder a la base de datos desde la lógica de negocios
        ArrayList<Usuario> usuarioDB = usuarioService.getAllUsuarios();
        // configurar el modelo a usar por thymeleaf
        usuarios.addAttribute("misusuarios", usuarioDB);
        return "usuarios/users";
    }

    // metodo para crear usuarios
    @GetMapping("createusuario")
    public String createUsuario(Model model){
        Usuario usuario = new Usuario();

        List<Empresa> listEmpresas = empresaService.getAllEmpresas();

        model.addAttribute("titulo", "Formulario: Nuevo Usuario");
        model.addAttribute("usuario", usuario);
        model.addAttribute("empresas", listEmpresas);
        model.addAttribute("roles", EnumUsuario.values());
        return "usuarios/createUser";
    }

    @GetMapping("/errorUsuario")
    public String error(){
        return "/usuarios/error";
    }

    @PostMapping("postUsuario")
    public RedirectView postUsuario(usuarioDTO data){

        Usuario usuario = new Usuario();

        usuario.setEmpresa(data.getEmpresa());
        usuario.setNombre(data.getNombre());
        usuario.setCorreo(data.getCorreo());
        usuario.setTelefono(data.getTelefono());
        usuario.setEnumUsuario(data.getEnumUsuario());

        Response response = usuarioService.postUsuario(usuario);
        if(response.getCode() == 200){
            return new RedirectView("/users");
        }
        return new RedirectView("/errorUsuario");
    }
    @GetMapping("/deleteusuario/{id}")
    public RedirectView deleteUsuario(@PathVariable int id){

        Response response = usuarioService.deleteUsuario(id);

        if(response.getCode()==200){
            return new RedirectView("/users");
        }
        return new RedirectView("/errorUsuario");

    }
}
