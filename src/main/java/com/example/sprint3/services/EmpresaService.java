package com.example.sprint3.services;

import com.example.sprint3.entities.Empresa;
import com.example.sprint3.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    // get all metodo
    public ArrayList<Empresa> getAllEmpresas() {
        return (ArrayList<Empresa>) empresaRepository.findAll();
    }

    // create find por id metodo
    public Empresa getEmpresa(int id) {
        return empresaRepository.findById(id).orElse(null);
    }

    // post metodo
    public Response postEmpresa(Empresa empresa) {
        Response response = new Response();
        empresaRepository.save(empresa);
        response.setCode(200);
        response.setMessage("Empresa añadida exitosamente");
        return response;

    }

    // delete metodo con try and catch
    public Response deleteEmpresa(int id) {
        Response response = new Response();
        try {
            empresaRepository.deleteById(id);
            response.setCode(200);
            response.setMessage("Empresa eliminada exitosamente");
            return response;
        }
        catch (Exception err){
            empresaRepository.deleteById(id);
            response.setCode(500);
            response.setMessage("Error: " + err.getMessage());
            return response;
        }
    }

    // patch método
    public Empresa updatedEmpresaWithMap(int id, Map<Object, Object> objectMap) {
        Empresa empresa = empresaRepository.findById(id).orElse(null);
        // obtiene el key y value, key nombreEmpresa. value= comfama
        objectMap.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(Empresa.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, empresa, value);
        });
        return empresaRepository.save(empresa);
    }
}
