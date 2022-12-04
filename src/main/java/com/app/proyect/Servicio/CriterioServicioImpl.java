package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.Criterio;
import com.app.proyect.Repositorio.CriterioRepositorio;

@Service
public class CriterioServicioImpl implements CriterioServicio{
	
	
	@Autowired
	private CriterioRepositorio repositorio;
	
	@Override
	public List<Criterio> listCriterios(){
		return repositorio.findAll();
	}

	@Override
	public Criterio insertCriterio(Criterio criterio) {
		return repositorio.save(criterio);
	}

	@Override
	public Criterio selectCriteriobyID(int id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Criterio updateCriterio(Criterio criterio) {
		return repositorio.save(criterio);
	}

	@Override
	public void deleteCriterio(int id) {
		repositorio.deleteById(id);
	}

	

}