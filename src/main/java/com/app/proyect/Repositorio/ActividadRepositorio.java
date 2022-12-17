package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyect.Modelo.Actividad;

@Repository
public interface ActividadRepositorio extends JpaRepository< Actividad, Integer> {

	
}