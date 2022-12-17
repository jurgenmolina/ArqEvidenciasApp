package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.ResultadoAprendizaje;
import com.app.proyect.Repositorio.ResultadoAprendizajeRepositorio;

@Service
public class ResultadoAprendizajeServicioImpl implements ResultadoAprendizajeServicio{
	
	
	@Autowired
	private ResultadoAprendizajeRepositorio repositorio;
	
	@Override
	public List<ResultadoAprendizaje> listResultadoAprendizaje(){
		return repositorio.findAll();
	}

	@Override
	public ResultadoAprendizaje insertResultadoAprendizaje(ResultadoAprendizaje ra) {
		return repositorio.save(ra);
	}

	@Override
	public ResultadoAprendizaje selectResultadoAprendizajebyID(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public ResultadoAprendizaje updateResultadoAprendizaje(ResultadoAprendizaje ra) {
		return repositorio.save(ra);
	}

	@Override
	public void deleteResultadoAprendizaje(int id) {
		repositorio.deleteById(id);
	}

	

}