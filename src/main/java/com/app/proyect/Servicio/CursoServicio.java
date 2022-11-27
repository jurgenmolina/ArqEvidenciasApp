package com.app.proyect.Servicio;

import java.util.List;

import com.app.proyect.Modelo.Curso;

public interface CursoServicio {
	
	public List<Curso> listCursos();
	
	public Curso insertCurso(Curso curso);
	
	public Curso selectCursobyID(int id);
	
	public Curso updateCurso(Curso curso);
	
	public void deleteCurso(int id);
}