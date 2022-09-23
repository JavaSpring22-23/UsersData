package com.example.sprint3.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Movimiento> movimiento;



    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;



    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @Column(name = "telefono")
    private int telefono;
    @Column(name = "perfil")
    private EnumUsuario enumUsuario;


    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono= telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public EnumUsuario getEnumUsuario() {
        return enumUsuario;
    }

    public void setEnumUsuario(EnumUsuario enumUsuario) {
        this.enumUsuario = enumUsuario;
    }

    public Set<Movimiento> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Set<Movimiento> movimiento) {
        this.movimiento = movimiento;
    }

    @JsonBackReference
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


}