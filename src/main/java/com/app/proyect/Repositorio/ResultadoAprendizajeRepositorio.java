package com.app.proyect.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.proyect.Modelo.ResultadoAprendizaje;

@Repository
public interface ResultadoAprendizajeRepositorio extends JpaRepository< ResultadoAprendizaje, Integer> {

	
}