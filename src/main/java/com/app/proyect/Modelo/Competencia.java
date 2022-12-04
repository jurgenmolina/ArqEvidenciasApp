package com.app.proyect.Modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "competencias")
public class Competencia {
	
	@Id
	@Column(name = "identificacion")
	private String identificación;
	
	@Column(name = "tipoCompetencia")
	private String tipoCompetencia;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "resultadoAprendizaje")
	private String resultadoAprendizaje;
	
	@OneToMany(mappedBy = "competencia", cascade = CascadeType.ALL)
	private Set<Criterio> criterios = new HashSet<>();

	public Competencia() {
		super();
	}

	public Competencia(String identificación, String tipoCompetencia, String nombre, String descripcion,
			String resultadoAprendizaje, Set<Criterio> criterios) {
		super();
		this.identificación = identificación;
		this.tipoCompetencia = tipoCompetencia;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.resultadoAprendizaje = resultadoAprendizaje;
		this.criterios = criterios;
	}

	public String getIdentificación() {
		return identificación;
	}

	public void setIdentificación(String identificación) {
		this.identificación = identificación;
	}

	public String getTipoCompetencia() {
		return tipoCompetencia;
	}

	public void setTipoCompetencia(String tipoCompetencia) {
		this.tipoCompetencia = tipoCompetencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResultadoAprendizaje() {
		return resultadoAprendizaje;
	}

	public void setResultadoAprendizaje(String resultadoAprendizaje) {
		this.resultadoAprendizaje = resultadoAprendizaje;
	}

	public Set<Criterio> getCriterios() {
		return criterios;
	}

	public void setCriterios(Set<Criterio> criterios) {
		this.criterios = criterios;
		for(Criterio criterio : criterios) {
			criterio.setCompetencia(this);
		}
	}

	@Override
	public String toString() {
		return "Competencia [identificación=" + identificación + ", tipoCompetencia=" + tipoCompetencia + ", nombre="
				+ nombre + "]";
	}
	
	

}
