package com.example.sprint3.services;

import com.example.sprint3.entities.Empresa;
import com.example.sprint3.entities.Login;
import com.example.sprint3.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    // Find all method for login
    public ArrayList<Login> getAllLogins() {
        return (ArrayList<Login>) loginRepository.findAll();
    }

    // Create login
    public Response loginUser(Login user){
        Response response = new Response();


        // Crear en el login repository un query que me permita validar las credenciales
        // Si llega algo al array eso indica que el usuario si ingreso los datos correctos
        ArrayList<Login> existe = loginRepository.validaCredenciales(user.getCorreoElectronico(), user.getPassword());


        // Validar correo electronico

        if(!isValidEmailAddress(user.getCorreoElectronico())){
            response.setCode(500);
            response.setMessage("Error, el usuario dado no es válido");
        }

        // validar password
        if(user.getPassword().equals("") || user.getPassword() == null){
            response.setCode(500);
            response.setMessage("Error, su contraseña no es válida");
            return response;
        }

        if(existe != null && existe.size() > 0){
            response.setCode(200);
            response.setMessage("Usuario Autenticado exitosamente");
            return response;
        }
        response.setCode(500);
        response.setMessage("Error, sus datos de acceso no son válidos");
        return response;
    }

    // Método para validar correo electronico

    public boolean isValidEmailAddress(String email){
        boolean result = true;
        try{
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex){
            result = false;
        }
        return result;
    }


//    public Response postUsuarioLogin(Login user) {
//        Response response = new Response();
//
//        loginRepository.save(user);
//        response.setCode(200);
//        response.setMessage("Usuario creado exitosamente");
//        return response;
//    }

    public Response createLoginUsuario(Login user) {
        Response response = new Response();

        // validad correo
        if(!isValidEmailAddress(user.getCorreoElectronico())){
            response.setCode(500);
            response.setMessage("Error, el usuario dado no es válido");
        }

        // validar password
        if(user.getPassword().equals("") || user.getPassword() == null){
            response.setCode(500);
            response.setMessage("Error, su contraseña no es válida");
            return response;
        }

        // validar que correo no esté repetido

        ArrayList<Login> existe = loginRepository.validaCorreo(user.getCorreoElectronico());

        if(existe != null && existe.size() > 0){
            response.setCode(500);
            response.setMessage("Error, El correo electronico ya está en uso");
            return response;
        }


        // Crea una instancia de la libreria que encripta la contraseña
        BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
        user.setPassword(encrypt.encode(user.getPassword()));


        this.loginRepository.save(user);
        response.setCode(200);
        response.setMessage("Usuario registrado exitosamente");
        return response;
    }

    public Login selectById(int Id){
        Optional<Login> existe = loginRepository.findById(Id);
        if(existe.isPresent()){
            return existe.get();
        }
        return null;
    }

    // Seleccionar por correo, se configura primero en el repository
    public Login selectByUsername(String correoElectronico){
        Login existe = loginRepository.findByCorreoElectronico(correoElectronico);
        return existe;
    }

}
