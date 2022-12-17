package com.app.proyect.Modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "actividades")
public class Actividad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre; 
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "fechaCreacion")
	private LocalDateTime fechaCreacion;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "resultadoAprendizaje_id") 
	private ResultadoAprendizaje resultadoAprendizaje;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Grupo_id")
	private Grupo grupo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Profesor_id")
	private Usuario profesor;
	
	@OneToMany(mappedBy = "actividad", cascade = CascadeType.MERGE)
	private List<ActividadEstudiante> actividadesEstudiantes = new ArrayList<>();
 
	public Actividad(){
		super();
	}

	public Actividad(String nombre, String descripcion, Grupo grupo, Usuario profesor) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.grupo = grupo;
		this.profesor = profesor;
	} 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public ResultadoAprendizaje getResultadoAprendizaje() {
		return resultadoAprendizaje;
	}

	public void setResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
		this.resultadoAprendizaje = resultadoAprendizaje;
	}
	
	@PrePersist
	public void asignarFechaCreacion() {
		fechaCreacion = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return  nombre ;
	}
	
	
	
} 
