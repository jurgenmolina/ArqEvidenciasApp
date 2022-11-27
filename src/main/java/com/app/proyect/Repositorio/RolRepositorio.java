package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Rol;

@Repository
public interface RolRepositorio extends JpaRepository< Rol, Integer> {

	
}