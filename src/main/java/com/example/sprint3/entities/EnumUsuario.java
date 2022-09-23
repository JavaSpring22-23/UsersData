package com.example.sprint3.entities;

public enum EnumUsuario {
    ADMINISTRADOR ("Administrador"),
    OPERATIVO("Operativo");

    private final String name;

    EnumUsuario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
