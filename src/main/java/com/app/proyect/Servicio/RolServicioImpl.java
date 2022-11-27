package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Rol;
import com.app.proyect.Repositorio.CursoRepositorio;
import com.app.proyect.Repositorio.RolRepositorio;

@Service
public class RolServicioImpl implements RolServicio{
	
	
	@Autowired
	private RolRepositorio repositorio;

	@Override
	public List<Rol> listRoles() {
		// TODO Auto-generated method stub
		return repositorio.findAll();
	}


}