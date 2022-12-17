package com.app.proyect.Modelo;

import java.util.HashSet;
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
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "semestre")
	private byte semestre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
	private Set<Grupo> grupos = new HashSet<>();
	
	public Curso() {
		super();
	}
	
	public Curso(String codigo, String nombre, byte semestre, String descripcion, Set<Grupo> grupos) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.semestre = semestre;
		this.descripcion = descripcion;
		this.grupos = grupos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public byte getSemestre() {
		return semestre;
	}

	public void setSemestre(byte semestre) {
		this.semestre = semestre;
		
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
		for(Grupo grupo : grupos) {
			grupo.setCurso(this);
		}
	}

	@Override
	public String toString() {
		return nombre ;
	}
	
	
	
	
}
