package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Grupo;

@Repository
public interface GrupoRepositorio extends JpaRepository< Grupo, Integer> {

	
}