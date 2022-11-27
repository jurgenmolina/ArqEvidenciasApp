package com.app.proyect.Modelo;

import java.util.Collection;
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo", nullable = true)
	private int codigo;
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	
	@Column(name = "documento")
	private String documento;

	@Column(name = "primer_nombre")
	private String primerNombre;
	
	@Column(name = "segundo_nombre")
	private String segundoNombre;

	@Column(name = "primer_apellido")
	private String primerApellido;
	
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "foto")
	private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn( name = "rol_id")
	private Rol rol;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Collection<Rol> roles;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinTable(name = "estudiantes_grupos", 
	joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id",referencedColumnName = "id")
	)
	private Set<Grupo> gruposEstudiantes ;
	
	@OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
	private Set<Grupo> gruposProfesores = new HashSet<>();

	public Usuario() {

	}
	
	public Usuario(int id, String primerNombre, String primerApellido, String email, String password) {
		super();
		this.id = id;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.email = email;
		this.password = password;
	}

	public Usuario(int codigo, String tipoDocumento, String documento, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String telefono, String email, String password, String foto,
			Rol rol) {
		super();
		this.codigo = codigo;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.foto = foto;
		this.rol = rol;
	}

	public Usuario(int id, int codigo, String tipoDocumento, String documento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String email,
			String password, String foto, Rol rol) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.foto = foto;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public void añadirRol(Rol rol) {
		this.roles.add(rol);
	}

	public void eliminarRol(Rol rol) {
		this.roles.remove(rol);
	}
	
	public void añadirGrupoEstudiante(Grupo grupo) {
		this.gruposEstudiantes.add(grupo);
	}

	public void eliminarGrupoEstudiante(Grupo grupo) {
		this.gruposEstudiantes.remove(grupo);
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public Set<Grupo> getGrupo() {
		return gruposEstudiantes;
	}
	
	public Set<Grupo> getGrupos() {
		return gruposEstudiantes;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.gruposEstudiantes = grupos;
	}
	
	public Set<Grupo> getGruposProfesores() {
		return gruposProfesores;
	}

	public void setGruposProfesores(Set<Grupo> gruposProfesores) {
		this.gruposProfesores = gruposProfesores;
		for(Grupo grupo : gruposProfesores) {
			grupo.setProfesor(this);
		}
	}

	@Override
	public String toString() {
		return  primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
	}

	

	
	
	
}
