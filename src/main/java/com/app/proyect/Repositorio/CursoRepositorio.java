package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.proyect.Modelo.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository< Curso, Integer> {

	
}