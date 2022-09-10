package com.example.sprint3.services;

import com.example.sprint3.entities.Usuario;
import com.example.sprint3.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // get all metodo
    public ArrayList<Usuario> getAllUsuarios() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    // create find por id metodo
    public Usuario getUsuario(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // post metodo
    public Response postUsuario(Usuario usuario) {
        Response response = new Response();
        usuarioRepository.save(usuario);
        response.setCode(200);
        response.setMessage("Usuario añadido exitosamente");
        return response;

    }

    // delete metodo con try and catch
    public Response deleteUsuario(int id) {
        Response response = new Response();
        try {
            usuarioRepository.deleteById(id);
            response.setCode(200);
            response.setMessage("Usuario eliminado exitosamente");
            return response;
        }
        catch (Exception err){
            usuarioRepository.deleteById(id);
            response.setCode(500);
            response.setMessage("Error: " + err.getMessage());
            return response;
        }
    }

    // patch método
    public Usuario updatedUsuarioWithMap(int id, Map<Object, Object> objectMap) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        // obtiene el key y value, key nombreUsario. value= comfama
        objectMap.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(Usuario.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, usuario, value);
        });
        return usuarioRepository.save(usuario);
    }

}
