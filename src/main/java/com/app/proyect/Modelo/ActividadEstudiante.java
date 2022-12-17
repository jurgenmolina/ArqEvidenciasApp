package com.app.proyect.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes_actividades")
public class ActividadEstudiante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "evidencia")
	private String evidencia;
	
	@ManyToOne
	@JoinColumn(name = "estudiante_id")
	private Usuario estudiante;
	
	@ManyToOne
	@JoinColumn(name = "actividad_id")
	private Actividad actividad;

	public ActividadEstudiante() {
		super();
	} 

	public ActividadEstudiante(Usuario estudiante, Actividad actividad) {
		super();
		this.estudiante = estudiante;
		this.actividad = actividad;
	}

	public ActividadEstudiante(String evidencia, Usuario estudiante, Actividad actividad) {
		super();
		this.evidencia = evidencia;
		this.estudiante = estudiante;
		this.actividad = actividad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String carga) {
		this.evidencia = carga;
	}

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	@Override
	public String toString() {
		return "ActividadEstudiante [id=" + id + ", evidencia=" + evidencia + ", estudiante=" + estudiante + ", actividad="
				+ actividad + "]";
	}
	
	
	
}

