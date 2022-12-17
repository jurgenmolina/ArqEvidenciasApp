package com.app.proyect.Modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Table;

@Entity
@Table(name = "grupos")
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "estudiantes_grupos", 
			joinColumns = @JoinColumn(name = "grupo_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id")
	)
	private List<Usuario> estudiantes = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "profesor_id")
	private Usuario profesor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "curso_id")
	private Curso curso;
	
	@OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
	private List<Actividad> actividades = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "grupos_competencias", 
			joinColumns = @JoinColumn(name = "grupo_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "competencia_id",referencedColumnName = "identificacion")
	)
	private List<Competencia> competencias = new ArrayList<>();
	
	public Grupo() {
		super();
	}

	public Grupo(int id, String nombre, String descripcion, List<Usuario> estudiantes, Usuario profesor, Curso curso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estudiantes = estudiantes;
		this.profesor = profesor;
		this.curso = curso;
	}

	public Grupo(String nombre, String descripcion, List<Usuario> estudiantes, Usuario profesor, Curso curso,
			List<Competencia> competencias) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estudiantes = estudiantes;
		this.profesor = profesor;
		this.curso = curso;
		this.competencias = competencias;
	}

	public List<Competencia> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competencia> competencias) {
		this.competencias = competencias;
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

	public List<Usuario> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Usuario> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public void añadirEstudiante(Usuario estudiante) {
		this.estudiantes.add(estudiante);
	}

	public void eliminarEstudiante(Usuario estudiante) {
		this.estudiantes.remove(estudiante);
	}

	public Usuario getProfesor() {
		return profesor;
	}

	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	public void añadirActividad(Actividad actividad) {
		this.actividades.add(actividad);
	}

	public void eliminarActividad(Actividad actividad) {
		this.actividades.remove(actividad);
	}
	
	public void añadirCompetencia(Competencia competencia) {
		this.competencias.add(competencia);
	}

	public void eliminarCompetencia(Competencia competencia) {
		this.competencias.remove(competencia);
	}

	@Override
	public String toString() {
		return "Grupo: id: "+ id + " [nombre=" + nombre + ", descripcion=" + descripcion + ", estudiantes=" + estudiantes
				+ ", profesor=" + profesor + ", curso=" + curso + "]";
	}
	
}
