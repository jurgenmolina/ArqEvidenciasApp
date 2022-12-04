package com.app.proyect.Servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.proyect.DTO.UsuarioRegistroDTO;
import com.app.proyect.Modelo.Curso;
import com.app.proyect.Modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public Usuario insertEstudiante(Usuario usuario);
	
	public Usuario insertProfesor(Usuario usuario);
	
	public Usuario selectUsuariobyEmail(String email);
	
	public Usuario selectUsuariobyId(int id);
	
	public Usuario updateUsuario(Usuario usuario);
	
	public void deleteUsuario(int id);

	public List<Usuario> listarUsuarios();
	
	public List<Usuario> listarProfesores();
	
	public List<Usuario> listarEstudiantes();
	
	
}
