package com.example.sprint3.controllers;

import com.example.sprint3.entities.Usuario;
import com.example.sprint3.services.Response;
import com.example.sprint3.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Get all metodo
    @RequestMapping("/users")
    public ArrayList<Usuario> getAllUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    // Get 1 por id método
    @RequestMapping("/users/{id}")
    public Usuario getUsuario(@PathVariable int id){
        return usuarioService.getUsuario(id);
    }

    // Post método
    @PostMapping("/users")
    public Response postUsuario(@RequestBody Usuario usuario){
        return usuarioService.postUsuario(usuario);
    }

    // Delete método
    @DeleteMapping("/users/{id}")
    public Response deleteUsuario(@PathVariable int id){
        return usuarioService.deleteUsuario(id);
    }

    // Patch método
    @PatchMapping(value = "/users/update/{id}")
    public Usuario updatedUsuarioWithMap(@PathVariable("id") int id, @RequestBody Map<Object, Object> objectMap){
        return usuarioService.updatedUsuarioWithMap(id, objectMap);
    }
}
