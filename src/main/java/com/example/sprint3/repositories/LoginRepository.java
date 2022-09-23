package com.example.sprint3.repositories;

import com.example.sprint3.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface LoginRepository extends JpaRepository <Login, Integer> {

    @Query("SELECT u from Login u WHERE u.correoElectronico = ?1 AND u.password = ?2")
    ArrayList<Login> validaCredenciales(String usuario, String password);

    @Query("SELECT u from Login u WHERE u.correoElectronico = ?1")
    ArrayList<Login> validaCorreo(String correo);

    @Query("SELECT u from Login u WHERE u.correoElectronico = ?1")
    Login findByCorreoElectronico(String correoElectronico);
}
