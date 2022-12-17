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
import com.app.proyect.Modelo.ResultadoAprendizaje;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.CompetenciaServicio;
import com.app.proyect.Servicio.CriterioServicio;
import com.app.proyect.Servicio.ResultadoAprendizajeServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class ResultadoAprendizajeControlador {

	@Autowired
	private ResultadoAprendizajeServicio resultadoAprendizajeServicio;
	
	@Autowired 
	private UsuarioServicio usuarioServicio;
	
	
	@GetMapping("/resultadoAprendizajes")
	public String showListResultadoAprendizaje(Model modelo) {
		
		List<ResultadoAprendizaje> listResultadoAprendizaje = resultadoAprendizajeServicio.listResultadoAprendizaje();
		modelo.addAttribute("listResultadoAprendizaje", listResultadoAprendizaje);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		return "resultadoAprendizajes";
	}
	
	@GetMapping("/resultadoAprendizajes/nuevo")
	public String showFormResultadoAprendizaje(Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		ResultadoAprendizaje resultadoAprendizaje = new ResultadoAprendizaje();
		modelo.addAttribute("resultadoAprendizaje", resultadoAprendizaje);
		return "crear_resultadoAprendizaje";
	}
	
	@PostMapping("/resultadoAprendizajes")
	public String insertarResultadoAprendizaje(@ModelAttribute("resultadoAprendizaje") ResultadoAprendizaje resultadoAprendizaje) {
		resultadoAprendizajeServicio.insertResultadoAprendizaje(resultadoAprendizaje);
		return "redirect:/resultadoAprendizajes";
	}
	
	@GetMapping("/resultadoAprendizajes/editar/{id}")
	public String showFormEditResultadoAprendizaje(@PathVariable int id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("resultadoAprendizaje", resultadoAprendizajeServicio.selectResultadoAprendizajebyID(id));
		return "editar_resultadoAprendizaje";
	}
	
	@PostMapping("/resultadoAprendizajes/{id}")
	public String updateResultadoAprendizaje(@PathVariable int id, @ModelAttribute("resultadoAprendizaje") ResultadoAprendizaje resultadoAprendizaje,
			Model modelo) {
	    
		ResultadoAprendizaje resultadoAprendizajeActual = resultadoAprendizajeServicio.selectResultadoAprendizajebyID(id);
		resultadoAprendizajeActual.setTipoEvidencia(resultadoAprendizaje.getTipoEvidencia());
		resultadoAprendizajeActual.setDescripcion(resultadoAprendizaje.getDescripcion());
		resultadoAprendizajeActual.setLinkGuia(resultadoAprendizaje.getLinkGuia());
		resultadoAprendizajeActual.setInstrumentoEvaluacion(resultadoAprendizaje.getInstrumentoEvaluacion());
		resultadoAprendizajeActual.setAdicional(resultadoAprendizaje.getAdicional());
		
		resultadoAprendizajeServicio.updateResultadoAprendizaje(resultadoAprendizajeActual);
		return "redirect:/resultadoAprendizajes";
	}
	
	@GetMapping("/resultadoAprendizajes/{id}")
	public String deleteResultadoAprendizaje(@PathVariable int id) {
		resultadoAprendizajeServicio.deleteResultadoAprendizaje(id);
		return "redirect:/resultadoAprendizajes";
	}
	
	
}
	