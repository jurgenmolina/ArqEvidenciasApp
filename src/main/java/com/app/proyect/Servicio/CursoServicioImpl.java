package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.Curso;
import com.app.proyect.Repositorio.CursoRepositorio;

@Service
public class CursoServicioImpl implements CursoServicio{
	
	
	@Autowired
	private CursoRepositorio repositorio;
	
	@Override
	public List<Curso> listCursos(){
		return repositorio.findAll();
	}

	@Override
	public Curso insertCurso(Curso curso) {
		return repositorio.save(curso);
	}

	@Override
	public Curso selectCursobyID(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Curso updateCurso(Curso curso) {
		return repositorio.save(curso);
	}

	@Override
	public void deleteCurso(int id) {
		repositorio.deleteById(id);
	}

	

}