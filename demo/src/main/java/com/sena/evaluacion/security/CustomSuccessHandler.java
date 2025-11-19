package com.sena.evaluacion.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String rol = authority.getAuthority();
            System.out.println("============= Rol detectado: " + rol + " =========");

            if (rol.equals("Administrador") ) {
            	 response.sendRedirect("/usuario/HistorialU");
                 System.out.println("Usuario logueado como admin");
                 return;
			}
            
            
        }

        // Fallback si no hay roles
        response.sendRedirect("/");
    }
}
