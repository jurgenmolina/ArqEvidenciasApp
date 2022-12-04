package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyect.Modelo.Criterio;

@Repository
public interface CriterioRepositorio extends JpaRepository< Criterio, Integer> {

	
}