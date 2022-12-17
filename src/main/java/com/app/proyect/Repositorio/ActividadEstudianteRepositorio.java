package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyect.Modelo.ActividadEstudiante;

@Repository
public interface ActividadEstudianteRepositorio extends JpaRepository< ActividadEstudiante, Integer> {

	
}