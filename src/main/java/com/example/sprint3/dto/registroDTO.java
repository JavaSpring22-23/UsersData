package com.example.sprint3.dto;

public class registroDTO {

    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String password;
    //Se crea este atributo para validad el password del usuario
    private String validaPassword;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidaPassword() {
        return validaPassword;
    }

    public void setValidaPassword(String validaPassword) {
        this.validaPassword = validaPassword;
    }
}
