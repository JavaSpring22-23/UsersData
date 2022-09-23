package com.example.sprint3.dto;

import com.example.sprint3.entities.Empresa;
import com.example.sprint3.entities.EnumUsuario;

public class usuarioDTO {

    private Empresa empresa;
    private String nombre;
    private String correo;
    private int telefono;
    private EnumUsuario enumUsuario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public EnumUsuario getEnumUsuario() {
        return enumUsuario;
    }

    public void setEnumUsuario(EnumUsuario enumUsuario) {
        this.enumUsuario = enumUsuario;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
