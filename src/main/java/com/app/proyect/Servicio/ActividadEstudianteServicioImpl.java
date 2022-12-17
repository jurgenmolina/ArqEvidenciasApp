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
public class ActividadEstudianteServicioImpl implements ActividadEstudianteServicio{
	
	
	@Autowired
	private ActividadEstudianteRepositorio repositorio;
	
	@Override
	public List<ActividadEstudiante> listActividadesEstudiante(){
		return repositorio.findAll();
	}

	@Override
	public ActividadEstudiante insertActividadEstudiante(ActividadEstudiante actividadEstudiante) {
		return repositorio.save(actividadEstudiante);
	}

	@Override
	public ActividadEstudiante selectActividadEstudiantebyID(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ActividadEstudiante updateActividadEstudiante(ActividadEstudiante actividadEstudiante) {
		return repositorio.save(actividadEstudiante);
	}

	@Override
	public void deleteActividadEstudiante(int id) {
		repositorio.deleteById(id);
	}

	

}