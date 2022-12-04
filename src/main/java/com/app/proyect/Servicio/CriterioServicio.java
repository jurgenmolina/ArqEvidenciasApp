package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Criterio;

public interface CriterioServicio {
	
	public List<Criterio> listCriterios();
	
	public Criterio insertCriterio(Criterio criterio);
	
	public Criterio selectCriteriobyID(int id);
	
	public Criterio updateCriterio(Criterio criterio);
	
	public void deleteCriterio(int id);
}