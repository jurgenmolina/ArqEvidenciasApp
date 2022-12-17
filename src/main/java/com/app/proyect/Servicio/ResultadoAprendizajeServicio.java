package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Criterio;
import com.app.proyect.Modelo.ResultadoAprendizaje;

public interface ResultadoAprendizajeServicio {
	
	public List<ResultadoAprendizaje> listResultadoAprendizaje();
	
	public ResultadoAprendizaje insertResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje);
	
	public ResultadoAprendizaje selectResultadoAprendizajebyID(int id);
	
	public ResultadoAprendizaje updateResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje);
	
	public void deleteResultadoAprendizaje(int id);
}