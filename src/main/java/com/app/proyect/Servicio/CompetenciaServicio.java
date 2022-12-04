package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Competencia;

public interface CompetenciaServicio {
	
	public List<Competencia> listCompetencias();
	
	public Competencia insertCompetencia(Competencia competencia);
	
	public Competencia selectCompetenciabyID(String id);
	
	public Competencia updateCompetencia(Competencia competencia);
	
	public void deleteCompetencia(String id);
}