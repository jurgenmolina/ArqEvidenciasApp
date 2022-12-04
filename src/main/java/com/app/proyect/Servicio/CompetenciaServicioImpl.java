package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.Competencia;
import com.app.proyect.Modelo.Curso;
import com.app.proyect.Repositorio.CompetenciaRepositorio;
import com.app.proyect.Repositorio.CursoRepositorio;

@Service
public class CompetenciaServicioImpl implements CompetenciaServicio{
	
	
	@Autowired
	private CompetenciaRepositorio repositorio;
	
	@Override
	public List<Competencia> listCompetencias(){
		return repositorio.findAll();
	}

	@Override
	public Competencia insertCompetencia(Competencia competencia) {
		return repositorio.save(competencia);
	}

	@Override
	public Competencia selectCompetenciabyID(String id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Competencia updateCompetencia(Competencia competencia) {
		return repositorio.save(competencia);
	}

	@Override
	public void deleteCompetencia(String id) {
		repositorio.deleteById(id);
	}

	

}