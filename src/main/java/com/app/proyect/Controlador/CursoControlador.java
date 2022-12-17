package com.app.proyect.Controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.CursoServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class CursoControlador {

	@Autowired
	private CursoServicio cursoServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/cursos")
	public String showListCursos(Model modelo) {
		
		List<Curso> listcurso = cursoServicio.listCursos();
		modelo.addAttribute("listcurso", listcurso);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		return "cursos";
	}
	
	@GetMapping("/cursos/nuevo")
	public String showFormCurso(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		Curso curso = new Curso();
		modelo.addAttribute("curso", curso);
		return "crear_curso";
	}
	
	@PostMapping("/cursos")
	public String insertarCurso(@ModelAttribute("curso") Curso curso) {
		cursoServicio.insertCurso(curso);
		return "redirect:/cursos";
	}
	
	@GetMapping("/cursos/editar/{id}")
	public String showFormEditCurso(@PathVariable int id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("curso", cursoServicio.selectCursobyID(id));
		return "editar_curso";
	}
	
	@PostMapping("/cursos/{id}")
	public String updateCurso(@PathVariable int id, @ModelAttribute("curso") Curso curso,
			Model modelo) {
	    
		Curso cursoActual = cursoServicio.selectCursobyID(id);
		cursoActual.setId(id);
		cursoActual.setCodigo(curso.getCodigo());
		cursoActual.setNombre(curso.getNombre());
		cursoActual.setDescripcion(curso.getDescripcion());
		cursoActual.setSemestre(curso.getSemestre());
		
		cursoServicio.updateCurso(cursoActual);
		return "redirect:/cursos";
	}
	
	@GetMapping("/cursos/{id}")
	public String deleteCurso(@PathVariable int id) {
		cursoServicio.deleteCurso(id);
		return "redirect:/cursos";
	}
	
	
}
	