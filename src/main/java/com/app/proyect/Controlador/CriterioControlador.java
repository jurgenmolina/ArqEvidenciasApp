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

import com.app.proyect.Modelo.Competencia;
import com.app.proyect.Modelo.Criterio;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.CompetenciaServicio;
import com.app.proyect.Servicio.CriterioServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class CriterioControlador {

	@Autowired
	private CriterioServicio criterioServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private CompetenciaServicio competenciaServicio;
	
	@GetMapping("/criterios")
	public String showListCriterio(Model modelo) {
		
		List<Criterio> listCriterio = criterioServicio.listCriterios();
		modelo.addAttribute("listCriterio", listCriterio);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		return "criterios";
	}
	
	@GetMapping("/criterios/nuevo")
	public String showFormCriterio(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		Criterio criterio = new Criterio();
		modelo.addAttribute("criterio", criterio);
		List<Competencia> listCompetencias = competenciaServicio.listCompetencias();
		modelo.addAttribute("listCompetencias", listCompetencias);
		return "crear_criterio";
	}
	
	@PostMapping("/criterios")
	public String insertarCriterio(@ModelAttribute("criterio") Criterio criterio) {
		criterioServicio.insertCriterio(criterio);
		return "redirect:/criterios";
	}
	
	@GetMapping("/criterios/editar/{id}")
	public String showFormEditCriterio(@PathVariable int id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<Competencia> listCompetencias = competenciaServicio.listCompetencias();
		modelo.addAttribute("listCompetencias", listCompetencias);
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("criterio", criterioServicio.selectCriteriobyID(id));
		return "editar_criterio";
	}
	
	@PostMapping("/criterios/{id}")
	public String updateCriterio(@PathVariable int id, @ModelAttribute("criterio") Criterio criterio,
			Model modelo) {
	    
		Criterio criterioActual = criterioServicio.selectCriteriobyID(id);
		criterioActual.setId(criterio.getId());
		criterioActual.setDescripcion(criterio.getDescripcion());
		criterioActual.setCompetencia(criterio.getCompetencia());
		
		criterioServicio.updateCriterio(criterioActual);
		return "redirect:/criterios";
	}
	
	@GetMapping("/criterios/{id}")
	public String deleteCriterio(@PathVariable int id) {
		criterioServicio.deleteCriterio(id);
		return "redirect:/criterios";
	}
	
	
}
	