package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.Grupo;
import com.app.proyect.Repositorio.GrupoRepositorio;

@Service
public class GrupoServicioImpl implements GrupoServicio{
	
	
	@Autowired
	private GrupoRepositorio repositorio;
	
	@Override
	public List<Grupo> listGrupos(){
		return repositorio.findAll();
	}

	@Override
	public Grupo insertGrupo(Grupo grupo) {
		return repositorio.save(grupo);
	}

	@Override
	public Grupo selectGrupobyID(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Grupo updateGrupo(Grupo grupo) {
		return repositorio.save(grupo);
	}

	@Override
	public void deleteGrupo(int id) {
		repositorio.deleteById(id);
	}

	

}