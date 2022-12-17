package com.app.proyect.Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo", nullable = false, length = 10)
	private int codigo;
	
	@Column(name = "tipo_documento",  nullable = false, length = 50)
	private String tipoDocumento;
	
	@Column(name = "documento", nullable = false, length = 15)
	private String documento;

	@Column(name = "primer_nombre", nullable = false, length = 256)
	private String primerNombre;
	
	@Column(name = "segundo_nombre", length = 256)
	private String segundoNombre;

	@Column(name = "primer_apellido", nullable = false, length = 256)
	private String primerApellido;
	
	@Column(name = "segundo_apellido", length = 256)
	private String segundoApellido;
	
	@Column(name = "telefono", nullable = false, length = 15)
	private String telefono;
	
	@Column(name = "email", nullable = false, length = 256)
	private String email;
	
	@Column(name = "password", nullable = false, length = 256)
	private String password;
	
	@Column(name = "foto", length = 8)
	private String foto;
	
	@Column(name = "semestre", nullable = false)
	private int semestre;	
	
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull(message = "Debe ingresar su fecha de nacimiento")
	@Column(name = "fechaNacimiento")
	private LocalDate fechaNacimiento;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Collection<Rol> roles;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "estudiantes_grupos", 
	joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id",referencedColumnName = "id")
	)
	private List<Grupo> grupos = new ArrayList<>();
	
	@OneToMany(mappedBy = "estudiante", cascade = CascadeType.MERGE)
	private List<ActividadEstudiante> actividadesEstudiantes = new ArrayList<>();
	
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
			String primerApellido, String segundoApellido, String telefono, String email, String password,
			String foto) {
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
	}

	public Usuario(int codigo, String tipoDocumento, String documento, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String telefono, String email, String password, String foto,
			Collection<Rol> roles) {
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
		this.roles = roles;
	}

	public Usuario(int id, int codigo, String tipoDocumento, String documento, String primerNombre,
			String segundoNombre, String primerApellido, String segundoApellido, String telefono, String email,
			String password, String foto, Set<Rol> roles) {
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
		this.roles = roles;
	}

	public Usuario(int codigo, String tipoDocumento, String documento, String primerNombre, String segundoNombre,
			String primerApellido, String segundoApellido, String telefono, String email, String password, String foto,
			int semestre, LocalDate fechaNacimiento) {
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
		this.semestre = semestre;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
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

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return  codigo + " " + primerNombre + " " + segundoNombre + " " + primerApellido + " " + segundoApellido;
	}

	
	
	
}
