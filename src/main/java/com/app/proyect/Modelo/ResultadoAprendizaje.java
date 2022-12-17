package com.app.proyect.Modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "resultados_de_aprendizaje")
public class ResultadoAprendizaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "tipoEvidencia")
	private String tipoEvidencia;
	
	@Column(name = "descripcion")
	private String descripcion; 
	
	@Column(name = "linkGuia")
	private String linkGuia;
	
	@Column(name = "instrumentoEvaluacion")
	private String instrumentoEvaluacion;
	
	@Column(name = "adicional")
	private String adicional;
	
	@OneToMany(mappedBy = "resultadoAprendizaje", cascade = CascadeType.ALL)
	private List<Actividad> actividades = new ArrayList<>();

	public ResultadoAprendizaje() {
		super();
	}

	public ResultadoAprendizaje(String tipoEvidencia, String descripcion, String linkGuia, String instrumentoEvaluacion,
			String adicional) {
		super();
		this.tipoEvidencia = tipoEvidencia;
		this.descripcion = descripcion;
		this.linkGuia = linkGuia;
		this.instrumentoEvaluacion = instrumentoEvaluacion;
		this.adicional = adicional;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoEvidencia() {
		return tipoEvidencia;
	}

	public void setTipoEvidencia(String tipoEvidencia) {
		this.tipoEvidencia = tipoEvidencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLinkGuia() {
		return linkGuia;
	}

	public void setLinkGuia(String linkGuia) {
		this.linkGuia = linkGuia;
	}

	public String getInstrumentoEvaluacion() {
		return instrumentoEvaluacion;
	}

	public void setInstrumentoEvaluacion(String instrumentoEvaluacion) {
		this.instrumentoEvaluacion = instrumentoEvaluacion;
	}

	public String getAdicional() {
		return adicional;
	}

	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}

	@Override
	public String toString() {
		return tipoEvidencia;
	}


}
