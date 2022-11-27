package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Grupo;

public interface GrupoServicio {
	
	public List<Grupo> listGrupos();
	
	public Grupo insertGrupo(Grupo grupo);
	
	public Grupo selectGrupobyID(int id);
	
	public Grupo updateGrupo(Grupo grupo);
	
	public void deleteGrupo(int id);
}