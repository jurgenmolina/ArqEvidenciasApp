package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyect.Modelo.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByEmail(String email);
	
	public Usuario findById(int id);
	
}
