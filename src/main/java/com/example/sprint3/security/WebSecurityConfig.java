package com.example.sprint3.security;


import com.example.sprint3.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements UserDetailsService{

    @Autowired
    private LoginService loginService;

    @Bean
    // Hay que darle permiso a todos los html y css
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // No se pueden hacer peticiones post si esta el csrf() habilitado
        http.csrf().disable()

                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/","/home","/**/*.css","/register","/postRegistro").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll().defaultSuccessUrl("/inicio", true)
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


    // Encriptar password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Se trae el usuario = correo y se trae el password y springboot hace el redireccionamento
        com.example.sprint3.entities.Login userObject = loginService.selectByUsername(username);
        System.out.println(username);
        if (userObject != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(
                    userObject.getCorreoElectronico(),
                    userObject.getPassword(),
                    authorities
            );
        }

        throw new UsernameNotFoundException(
                "User '" + username + "' not found.");
    }


    // Configuración por default
    /* @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    } */

}
