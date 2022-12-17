package com.app.proyect.Controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.proyect.Modelo.Actividad;
import com.app.proyect.Modelo.ActividadEstudiante;
import com.app.proyect.Modelo.Competencia;
import com.app.proyect.Modelo.Grupo;
import com.app.proyect.Modelo.ResultadoAprendizaje;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Servicio.ActividadEstudianteServicio;
import com.app.proyect.Servicio.ActividadServicio;
import com.app.proyect.Servicio.CompetenciaServicio;
import com.app.proyect.Servicio.GrupoServicio;
import com.app.proyect.Servicio.ResultadoAprendizajeServicio;
import com.app.proyect.Servicio.UsuarioServicio;

@Controller
public class ActividadControlador {

	@Autowired
	private ActividadServicio actividadServicio;
	
	@Autowired
	private ActividadEstudianteServicio actividadEstudianteServicio;
	
	@Autowired
	private GrupoServicio grupoServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private CompetenciaServicio competenciaServicio;
	
	@Autowired
	private ResultadoAprendizajeServicio raServicio;
	
	@GetMapping("/actividades/{id}")
	public String viewActividades(@PathVariable int id, Model modelo) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("grupo", grupoServicio.selectGrupobyID(id));
		List<Actividad> listActividades = actividadServicio.listActividades();
		
		List<Actividad> newList = new ArrayList<>();
		
	    for (Actividad actividad : listActividades) { 
	        if (grupoServicio.selectGrupobyID(id).getActividades().contains(actividad)) { 
	            newList.add(actividad); 
	        } 
	    }
	    
	    List<ActividadEstudiante> aes = actividadEstudianteServicio.listActividadesEstudiante();
	    List<Integer> ids = new ArrayList<>();
	    
	    for (ActividadEstudiante ae : aes) {
			if (ae.getEvidencia() != null && ae.getEstudiante().getId() == usuario.getId()) {
				ids.add(ae.getActividad().getId());
			}
		}
	    
	    modelo.addAttribute("ids", ids);
	    modelo.addAttribute("listActividades", newList);
		return "verGrupo";
	}
	
	@PostMapping("/actividades/nuevo")
	public String insertarActividad(@ModelAttribute("grupo") Grupo grupo, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		modelo.addAttribute("usuario", usuario);
		Grupo grupoActual = grupoServicio.selectGrupobyID(grupo.getId());
		Actividad actividad = new Actividad();
		actividad.setGrupo(grupoActual);
		actividad.setProfesor(usuarioServicio.selectUsuariobyId(grupoActual.getProfesor().getId()));
		List<ResultadoAprendizaje> listRAs = raServicio.listResultadoAprendizaje();
		
		modelo.addAttribute("listRAs", listRAs);
		modelo.addAttribute("grupo_id", grupoActual.getId());
		modelo.addAttribute("actividad", actividad);
		return "crear_actividad";
	}
	
	@PostMapping("/actividades")
	public String insertarActividad(@ModelAttribute("actividad") Actividad actividad) {
		actividadServicio.insertActividad(actividad);
		
		for (Usuario estudiante : actividad.getGrupo().getEstudiantes()) {
			ActividadEstudiante ae = new ActividadEstudiante(usuarioServicio.selectUsuariobyId(estudiante.getId()), actividadServicio.selectActividadbyID(actividad.getId()));
			actividadEstudianteServicio.insertActividadEstudiante(ae);
		}
		
		return "redirect:/actividades/" + actividad.getGrupo().getId();
	}
	
	@GetMapping("/actividades/{id_grupo}/ver")
	public String showFormEditActividad(@PathVariable int id_grupo, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		Grupo grupo = grupoServicio.selectGrupobyID(id_grupo);
		modelo.addAttribute("grupo", grupo);
		modelo.addAttribute("listEstudiantes", grupo.getEstudiantes());
		modelo.addAttribute("usuario", usuario);
		return "estudiantesGrupo";
	}

	@GetMapping("/actividades/{id_grupo}/editar/{id_actividad}")
	public String showFormEditActividad(@PathVariable int id_grupo,@PathVariable int id_actividad, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<ResultadoAprendizaje> listRAs = raServicio.listResultadoAprendizaje();
		modelo.addAttribute("listRAs", listRAs);
		modelo.addAttribute("grupo", grupoServicio.selectGrupobyID(id_grupo));
		modelo.addAttribute("actividad", actividadServicio.selectActividadbyID(id_actividad));
		modelo.addAttribute("usuario", usuario);
		return "editar_actividad";
	}
	
	@PostMapping("/actividades/{id_grupo}/{id_actividad}")
	public String updateActividad(@PathVariable int id_grupo, @PathVariable int id_actividad, @ModelAttribute("actividad") Actividad actividad,
			Model modelo) {
		Actividad actividadActual = actividadServicio.selectActividadbyID(id_actividad);
		actividadActual.setNombre(actividad.getNombre());
		actividadActual.setDescripcion(actividad.getDescripcion());
		actividadActual.setResultadoAprendizaje(actividad.getResultadoAprendizaje());
		actividadServicio.updateActividad(actividadActual);
		return "redirect:/actividades/" + id_grupo;
	}
	
	@GetMapping("/actividades/{id_grupo}/{id_actividad}")
	public String showFormSubirActividad(@PathVariable int id_grupo,@PathVariable int id_actividad, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		ActividadEstudiante actividadEstudiante = new ActividadEstudiante();
		List<ActividadEstudiante> aEstudiantes = actividadEstudianteServicio.listActividadesEstudiante();
		for (ActividadEstudiante ae : aEstudiantes) {
			if (ae.getActividad().getId() == id_actividad && ae.getEstudiante().getId() == usuario.getId()) {
				actividadEstudiante.setId(ae.getId());
				actividadEstudiante.setActividad(ae.getActividad());
				actividadEstudiante.setEstudiante(ae.getEstudiante());
				actividadEstudiante.setEvidencia(ae.getEvidencia());
			}
		}
		
		modelo.addAttribute("actividadEstudiante", actividadEstudiante);
		modelo.addAttribute("grupo", grupoServicio.selectGrupobyID(id_grupo));
		modelo.addAttribute("actividad", actividadServicio.selectActividadbyID(id_actividad));
		modelo.addAttribute("usuario", usuario);
		return "subir_evidencia";
	}
	
	@PostMapping("/actividades/{id_grupo}/{id_actividad}/{id_actividadEstudiante}")
	public String updateActividadEstudiante(@PathVariable int id_grupo, @PathVariable int id_actividad, @PathVariable int id_actividadEstudiante, 
			@ModelAttribute("actividadEstudiante") ActividadEstudiante actividadEstudiante,
			Model modelo, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {
		
		if (file == null || file.isEmpty()) {
			attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
			return "redirect:/actividades/" + id_grupo + "/" + id_actividad;
		}

		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("user.home"));
		builder.append(File.separator);
		builder.append("spring_upload_example");
		builder.append(File.separator);
		builder.append(file.getOriginalFilename());
		byte[] fileBytes = file.getBytes();
		Path path = Paths.get(builder.toString());
		Files.write(path, fileBytes);
		
		attributes.addFlashAttribute("message", "Archivo cargado correctamente ["+builder.toString()+"]");
		
		ActividadEstudiante aEstudiante = actividadEstudianteServicio.selectActividadEstudiantebyID(id_actividadEstudiante);
		aEstudiante.setEvidencia(file.getOriginalFilename());
		actividadEstudianteServicio.updateActividadEstudiante(aEstudiante);
		return "redirect:/actividades/" + id_grupo;
	}
	 
	@GetMapping("/actividades/{id_grupo}/delete/{id_actividad}")
	public String deleteActividad(@PathVariable int id_grupo, @PathVariable int id_actividad) {
				
		List<ActividadEstudiante> aEstudiantes = actividadEstudianteServicio.listActividadesEstudiante();
		
		for (ActividadEstudiante actividadEstudiante : aEstudiantes) {
			if (actividadEstudiante.getActividad().equals(actividadServicio.selectActividadbyID(id_actividad))) {
				actividadEstudianteServicio.deleteActividadEstudiante(actividadEstudiante.getId());
			}
		}
		
		actividadServicio.deleteActividad(id_actividad);
		Grupo grupo = grupoServicio.selectGrupobyID(id_grupo);
		return "redirect:/actividades/" + grupo.getId();
	}
	
	@GetMapping("/actividades/agregarCompetencias/{id}")
	public String showFormAgregarCompetencias(@PathVariable int id, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<Competencia> listCompetencias = competenciaServicio.listCompetencias();
		Grupo grupo = grupoServicio.selectGrupobyID(id);
		
		List<Competencia> newListG = new ArrayList<>();
		List<Competencia> newListE = new ArrayList<>();
		
	    for (Competencia competencia : listCompetencias) { 
	        if (!grupo.getCompetencias().contains(competencia)) {
	        	if (competencia.getTipoCompetencia().equals("GENERICA")) {
	        		newListG.add(competencia); 
				}else
	            newListE.add(competencia); 
	        } 
	    }
	    
		modelo.addAttribute("grupo", grupo);
	    modelo.addAttribute("listCompetencias", grupo.getCompetencias());
		modelo.addAttribute("listCompetenciaGenerica", newListG);
		modelo.addAttribute("listCompetenciaEspecifica", newListE);
		modelo.addAttribute("usuario", usuario);
		return "grupo_agregar_competencias";
	}
	
	@PostMapping("/actividades/agregarCompetencias/{id}")
	public String addCompetenciasGrupo(@PathVariable int id, @ModelAttribute("grupo") Grupo grupo,
			Model modelo) {
	    
		Grupo grupoActual = grupoServicio.selectGrupobyID(id);
		
		for (Competencia competencia : grupo.getCompetencias()) {
			grupoActual.añadirCompetencia(competencia);
		}
		grupoServicio.updateGrupo(grupoActual);
		return "redirect:/actividades/agregarCompetencias/" + grupoActual.getId();
	}
	
	@GetMapping("/actividades/agregarCompetencias/{id_grupo}/{id_competencia}")
	public String deleteGrupo(@PathVariable int id_grupo, @PathVariable String id_competencia) {
		Grupo grupoActual = grupoServicio.selectGrupobyID(id_grupo);
		Competencia competencia = competenciaServicio.selectCompetenciabyID(id_competencia);
		
		grupoActual.eliminarCompetencia(competencia);
		grupoServicio.updateGrupo(grupoActual);
		return "redirect:/actividades/agregarCompetencias/" + grupoActual.getId();
	}
	
	@GetMapping("/actividades/{id_grupo}/{id_actividad}/entregas")
	public String showVerEntregas(@PathVariable int id_grupo,@PathVariable int id_actividad, Model modelo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = usuarioServicio.selectUsuariobyEmail(auth.getName());
		List<ActividadEstudiante> aes = actividadEstudianteServicio.listActividadesEstudiante();
		List<ActividadEstudiante> auxList = new ArrayList<>();
		for (ActividadEstudiante actividadEstudiante : aes) {
			if (actividadEstudiante.getActividad().getId() == id_actividad) {
				auxList.add(actividadEstudiante);
			}
		}
		
		modelo.addAttribute("grupo", grupoServicio.selectGrupobyID(id_grupo));
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("listActividadesEstudiante", auxList);
		
		return "verEntregas";
	}
	
}
	