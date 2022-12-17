package com.app.proyect.Controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.proyect.Modelo.Criterio;
import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Grupo;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.CursoServicio;
import com.app.proyect.Servicio.GrupoServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class GrupoControlador {

	@Autowired
	private GrupoServicio grupoServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private CursoServicio cursoServicio;
	
	@GetMapping("/grupos")
	public String showListGrupos(Model modelo) {
		
		List<Grupo> listGrupos = grupoServicio.listGrupos();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<Grupo> listGruposProfesores = new ArrayList<>();
		
		for (Grupo grupo : listGrupos) {
			if (grupo.getProfesor().equals(usuario)) {
				listGruposProfesores.add(grupo);
			}
		}
		
		List<Grupo> listGruposEstudiantes = new ArrayList<>();
		
		for (Grupo grupo : listGrupos) {
			if (grupo.getEstudiantes().contains(usuario)) {
				System.out.println(grupo.getEstudiantes());
				System.out.println(usuario);
				listGruposEstudiantes.add(grupo);
			}
		}
		modelo.addAttribute("listGruposEstudiantes", listGruposEstudiantes);
		modelo.addAttribute("listGruposProfesores", listGruposProfesores);
		modelo.addAttribute("listGrupos", listGrupos);
		modelo.addAttribute("usuario", usuario);
		return "grupos";
	}
	
	@GetMapping("/grupos/nuevo")
	public String showFormGrupo(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<Usuario> listProfesores = usuarioServicio.listarProfesores();
		List<Curso> listCursos = cursoServicio.listCursos();
		Grupo grupo = new Grupo();
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("listProfesores", listProfesores);
		modelo.addAttribute("listCursos", listCursos);
		modelo.addAttribute("grupo", grupo);
		return "crear_grupo";
	}
	
	@PostMapping("/grupos")
	public String insertarGrupo(@ModelAttribute("grupo") Grupo grupo) {
		grupoServicio.insertGrupo(grupo);
		return "redirect:/grupos";
	}
	
	@GetMapping("/grupos/editar/{id}")
	public String showFormEditGrupo(@PathVariable int id, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<Usuario> listProfesores = usuarioServicio.listarProfesores();
		List<Curso> listCursos = cursoServicio.listCursos();
		modelo.addAttribute("grupo", grupoServicio.selectGrupobyID(id));
		modelo.addAttribute("listCursos", listCursos);
		modelo.addAttribute("listProfesores", listProfesores);
		modelo.addAttribute("usuario", usuario);
		return "editar_grupo";
	}
	
	@PostMapping("/grupos/{id}")
	public String updateGrupo(@PathVariable int id, @ModelAttribute("grupo") Grupo grupo,
			Model modelo) {
	    
		Grupo grupoActual = grupoServicio.selectGrupobyID(id);
		grupoActual.setId(id);
		grupoActual.setNombre(grupo.getNombre());
		grupoActual.setDescripcion(grupo.getDescripcion());
		grupoActual.setCurso(grupo.getCurso());
		grupoActual.setProfesor(grupo.getProfesor());
		grupoServicio.updateGrupo(grupoActual);
		return "redirect:/grupos";
	}
	
	@GetMapping("/grupos/{id}")
	public String deleteGrupo(@PathVariable int id) {
		grupoServicio.deleteGrupo(id);
		return "redirect:/grupos";
	}
	

	@GetMapping("/grupos/agregarEstudiantes/{id}")
	public String showFormAgregarEstudiantes(@PathVariable int id, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<Usuario> listEstudiantes = usuarioServicio.listarEstudiantes();
		Grupo grupo = grupoServicio.selectGrupobyID(id);
		
		List<Usuario> newList = new ArrayList<>();
		
	    for (Usuario estudiante : listEstudiantes) { 
	        if (!grupo.getEstudiantes().contains(estudiante)) { 
	            newList.add(estudiante); 
	        } 
	    }
		
		modelo.addAttribute("grupo", grupo);
		modelo.addAttribute("listEstudiantes", newList);
		modelo.addAttribute("usuario", usuario);
		return "grupo_agregar_estudiantes";
	}
	
	@PostMapping("/grupos/agregarEstudiantes/{id}")
	public String addEstudiantesGrupo(@PathVariable int id, @ModelAttribute("grupo") Grupo grupo,
			Model modelo) {
	    
		Grupo grupoActual = grupoServicio.selectGrupobyID(id);
		
		for (Usuario estudiante : grupo.getEstudiantes()) {
			grupoActual.añadirEstudiante(estudiante);
		} 
		grupoServicio.updateGrupo(grupoActual);
		return "redirect:/grupos";
	}
	
	
}
	