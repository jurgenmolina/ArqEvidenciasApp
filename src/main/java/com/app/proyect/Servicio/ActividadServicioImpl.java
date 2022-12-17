package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.Actividad;
import com.app.proyect.Modelo.ActividadEstudiante;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Repositorio.ActividadEstudianteRepositorio;
import com.app.proyect.Repositorio.ActividadRepositorio;

@Service
public class ActividadServicioImpl implements ActividadServicio{
	
	
	@Autowired
	private ActividadRepositorio repositorio;
	
	@Autowired
	private ActividadEstudianteRepositorio repositorio2;
	
	@Override
	public List<Actividad> listActividades(){
		return repositorio.findAll();
	}

	@Override
	public Actividad insertActividad(Actividad actividad) {
		
		return repositorio.save(actividad);
	}

	@Override
	public Actividad selectActividadbyID(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Actividad updateActividad(Actividad actividad) {
		return repositorio.save(actividad);
	}

	@Override
	public void deleteActividad(int id) {
		repositorio.deleteById(id);
	}

	

}