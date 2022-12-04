package com.app.proyect.Controlador;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.proyect.DTO.UsuarioRegistroDTO;
import com.app.proyect.Modelo.Rol;
import com.app.proyect.Servicio.RolServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroControlador {

	@Lazy
	@Autowired
	private UsuarioServicio usuarioServicio;

	public RegistroControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {		
		usuarioServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}