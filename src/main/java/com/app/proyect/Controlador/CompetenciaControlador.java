package com.app.proyect.Controlador;

import java.util.ArrayList;
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

import com.app.proyect.Modelo.Competencia;
import com.app.proyect.Modelo.Criterio;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.CompetenciaServicio;
import com.app.proyect.Servicio.CriterioServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class CompetenciaControlador {

	@Autowired
	private CompetenciaServicio competenciaServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private CriterioServicio criterioServicio;
	
	@GetMapping("/competencias")
	public String showListCompetencia(Model modelo) {
		
		List<Competencia> listCompetencia = competenciaServicio.listCompetencias();
		modelo.addAttribute("listCompetencia", listCompetencia);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		return "competencias";
	}
	
	@GetMapping("/competencias/nuevo")
	public String showFormCompetencia(Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		Competencia competencia = new Competencia();
		modelo.addAttribute("competencia", competencia);
		return "crear_competencia";
	}
	
	@PostMapping("/competencias")
	public String insertarCompetencia(@ModelAttribute("competencia") Competencia competencia) {
		competenciaServicio.insertCompetencia(competencia);
		return "redirect:/competencias";
	}
	
	@GetMapping("/competencias/ver/{id}")
	public String viewProyecto(@PathVariable String id, Model modelo) {
		modelo.addAttribute("competencia", competenciaServicio.selectCompetenciabyID(id));
		List<Criterio> listCriterio = criterioServicio.listCriterios();
		
		List<Criterio> newList = new ArrayList<>();
		
	    for (Criterio criterio : listCriterio) { 
	        if (competenciaServicio.selectCompetenciabyID(id).getCriterios().contains(criterio)) { 
	            newList.add(criterio); 
	        } 
	    }
		
		modelo.addAttribute("listCriterio", newList);
		return "viewCC";
	}
	
	@GetMapping("/competencias/editar/{id}")
	public String showFormEditCompetencia(@PathVariable String id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("competencia", competenciaServicio.selectCompetenciabyID(id));
		return "editar_competencia";
	}
	
	@PostMapping("/competencias/{id}")
	public String updateCompetencia(@PathVariable String id, @ModelAttribute("competencia") Competencia competencia,
			Model modelo) {
	    
		Competencia competenciaActual = competenciaServicio.selectCompetenciabyID(id);
		competenciaActual.setIdentificacion(competencia.getIdentificacion());
		competenciaActual.setNombre(competencia.getNombre());
		competenciaActual.setDescripcion(competencia.getDescripcion());
		competenciaActual.setTipoCompetencia(competencia.getTipoCompetencia());
		competenciaActual.setResultadoAprendizaje(competencia.getResultadoAprendizaje());
		competenciaActual.setCriterios(competencia.getCriterios());
		
		competenciaServicio.updateCompetencia(competenciaActual);
		return "redirect:/competencias";
	}
	
	@GetMapping("/competencias/{id}")
	public String deleteCompetencia(@PathVariable String id) {
		competenciaServicio.deleteCompetencia(id);
		return "redirect:/competencias";
	}
	
	
}
	