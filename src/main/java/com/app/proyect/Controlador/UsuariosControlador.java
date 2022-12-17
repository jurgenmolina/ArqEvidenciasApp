package com.app.proyect.Controlador;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Rol;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.RolServicio;
import com.app.proyect.Servicio.UsuarioServicio;


@Controller
public class UsuariosControlador {
	
	@Lazy
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private RolServicio rolServicio;
	
	@GetMapping("/error")
	public String accesoDenegado() {
		return "error";
	}
	
//	Perfil de usuarios 
	
	@GetMapping("/perfil")
	public String showPerfil(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		return "perfil_usuario";
	}
	
//	Gestion de profesores
	
	@GetMapping("/profesores")
	public String showListProfesores(Model modelo) {
		
		List<Usuario> listProfesores = usuarioServicio.listarProfesores();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("listProfesores", listProfesores);
		return "profesores";
	}
	
	@GetMapping("/profesores/nuevo")
	public String showFormProfesor(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		Usuario profesor = new Usuario();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("profesor", profesor);
		return "crear_profesor";
	}
	
	@PostMapping("/profesores")
	public String insertarProfesor(@ModelAttribute("profesor") Usuario profesor) {
		usuarioServicio.insertProfesor(profesor);
		return "redirect:/profesores";
	}
	
	@GetMapping("/profesores/editar/{id}")
	public String showFormEditProfesor(@PathVariable int id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("profesor", usuarioServicio.selectUsuariobyId(id));
		modelo.addAttribute("usuario", usuario);
		return "editar_profesor";
	}
	
	@PostMapping("/profesores/{id}")
	public String updateProfesor(@PathVariable int id, @ModelAttribute("profesor") Usuario usuario,
			Model modelo) {
	    
		Usuario usuarioActual = usuarioServicio.selectUsuariobyId(id);
		usuarioActual.setId(id);
		usuarioActual.setCodigo(usuario.getCodigo());
		usuarioActual.setTipoDocumento(usuario.getTipoDocumento());
		usuarioActual.setDocumento(usuario.getDocumento());
		usuarioActual.setPrimerNombre(usuario.getPrimerNombre());
		usuarioActual.setSegundoNombre(usuario.getSegundoNombre());
		usuarioActual.setPrimerApellido(usuario.getPrimerApellido());
		usuarioActual.setSegundoApellido(usuario.getSegundoApellido());
		usuarioActual.setTelefono(usuario.getTelefono());
		usuarioActual.setEmail(usuario.getEmail());
		usuarioActual.setPassword(usuario.getPassword());
		usuarioActual.setFoto(usuario.getFoto());
		usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioServicio.updateUsuario(usuarioActual);
		return "redirect:/profesores";
	}
	
	@GetMapping("/profesores/{id}")
	public String deleteProfesor(@PathVariable int id) {
		usuarioServicio.deleteUsuario(id);
		return "redirect:/profesores";
	}
	
//	Gestion de Estudiantes
	
	@GetMapping("/estudiantes")
	public String showListEstudiantes(Model modelo) {
		
		List<Usuario> listEstudiantes = usuarioServicio.listarEstudiantes();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("listEstudiantes", listEstudiantes);
		modelo.addAttribute("usuario", usuario);
		return "estudiantes";
	}
	
	@GetMapping("/estudiantes/nuevo")
	public String showFormEstudiante(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		Usuario estudiante = new Usuario();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("estudiante", estudiante);
		return "crear_estudiante";
	}
	
	@PostMapping("/estudiantes")
	public String insertarEstudiante(@ModelAttribute("estudiante") Usuario estudiante) {
				
		usuarioServicio.insertEstudiante(estudiante);
		return "redirect:/estudiantes";
	}
	

	@GetMapping("/estudiantes/editar/{id}")
	public String showFormEditEstudiante(@PathVariable int id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("estudiante", usuarioServicio.selectUsuariobyId(id));
		modelo.addAttribute("usuario", usuario);
		return "editar_estudiante";
	}
	
	@PostMapping("/estudiantes/{id}")
	public String updateEstudiante(@PathVariable int id, @ModelAttribute("estudiante") Usuario usuario,
			Model modelo) {
	    
		Usuario usuarioActual = usuarioServicio.selectUsuariobyId(id);
		usuarioActual.setId(id);
		usuarioActual.setCodigo(usuario.getCodigo());
		usuarioActual.setTipoDocumento(usuario.getTipoDocumento());
		usuarioActual.setDocumento(usuario.getDocumento());
		usuarioActual.setPrimerNombre(usuario.getPrimerNombre());
		usuarioActual.setSegundoNombre(usuario.getSegundoNombre());
		usuarioActual.setPrimerApellido(usuario.getPrimerApellido());
		usuarioActual.setSegundoApellido(usuario.getSegundoApellido());
		usuarioActual.setTelefono(usuario.getTelefono());
		usuarioActual.setEmail(usuario.getEmail());
		usuarioActual.setPassword(usuario.getPassword());
		usuarioActual.setFoto(usuario.getFoto());
		usuarioActual.setFechaNacimiento(usuario.getFechaNacimiento());
		usuarioActual.setSemestre(usuario.getSemestre());
		usuarioServicio.updateUsuario(usuarioActual);
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/estudiantes/{id}")
	public String deleteEstudiante(@PathVariable int id) {
		usuarioServicio.deleteUsuario(id);
		return "redirect:/estudiantes";
	}	
	
}