package com.example.sprint3.entities;


import javax.persistence.*;

@Entity
@Table(name = "movimientos")
public class Movimiento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;
    @Column(name = "monto")
    private int monto;
    @Column(name = "concepto")
    private String concepto;



    public Movimiento() {
    }

    public Movimiento(int id, Usuario usuario, int monto, String concepto) {
        this.id = id;
        this.usuario = usuario;
        this.monto = monto;
        this.concepto = concepto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
