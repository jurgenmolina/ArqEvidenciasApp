package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Actividad;

public interface ActividadServicio {
	
	public List<Actividad> listActividades();
	
	public Actividad insertActividad(Actividad actividad);
	
	public Actividad selectActividadbyID(int id);
	
	public Actividad updateActividad(Actividad actividad);
	
	public void deleteActividad(int id);
}