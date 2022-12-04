package com.app.proyect.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.proyect.Modelo.Rol;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Repositorio.RolRepositorio;
import com.app.proyect.Servicio.RolServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class LoginControlador {
	
	@Lazy
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(@Lazy Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		return "index";
	}

}
