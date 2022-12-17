package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Actividad;
import com.app.proyect.Modelo.ActividadEstudiante;
import com.app.proyect.Modelo.Usuario;

public interface ActividadEstudianteServicio {
	
	public List<ActividadEstudiante> listActividadesEstudiante();
	
	public ActividadEstudiante insertActividadEstudiante(ActividadEstudiante actividadEstudiante);
	
	public ActividadEstudiante selectActividadEstudiantebyID(int id);
	
	public ActividadEstudiante updateActividadEstudiante(ActividadEstudiante actividadEstudiante);
	
	public void deleteActividadEstudiante(int id);
}