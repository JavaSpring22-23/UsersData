package com.example.sprint3.services;

import com.example.sprint3.entities.Movimiento;
import com.example.sprint3.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    // Get all method
    public ArrayList<Movimiento> getAllMovimientos() {
        return (ArrayList<Movimiento>) movimientoRepository.findAll();
    }

    // get one method
    public Movimiento getMovimiento(int id) {
        return movimientoRepository.findById(id).orElse(null);
    }


    public Response postMovimiento(Movimiento movimiento) {
        Response response = new Response();
        movimientoRepository.save(movimiento);
        response.setCode(200);
        response.setMessage("Movimiento registrado exitosamente");
        return response;
    }

    public Response deleteMovimiento(int id){
        Response response = new Response();
        movimientoRepository.deleteById(id);
        response.setCode(200);
        response.setMessage("Movimiento borrado con exito");
        return response;
    }

    public Movimiento updatedMovimientoWithMap(int id, Map<Object, Object> objectMap) {
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        // obtiene el key y value, key nombreMovimiento
        objectMap.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(Movimiento.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, movimiento, value);
        });
        return movimientoRepository.save(movimiento);

    }
}
