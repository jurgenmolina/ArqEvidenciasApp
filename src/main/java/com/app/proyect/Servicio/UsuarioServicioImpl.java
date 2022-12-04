package com.app.proyect.Servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.proyect.DTO.UsuarioRegistroDTO;
import com.app.proyect.Modelo.Rol;
import com.app.proyect.Modelo.Usuario;
import com.app.proyect.Repositorio.RolRepositorio;
import com.app.proyect.Repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private RolRepositorio rolRepositorio;
	
	@Lazy
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		
		List<Rol> rolList = rolRepositorio.findAll();
		
		Usuario usuario = new Usuario(registroDTO.getCodigo(), registroDTO.getTipoDocumento(),
				registroDTO.getDocumento(), registroDTO.getPrimerNombre(), registroDTO.getSegundoNombre(),
				registroDTO.getPrimerApellido(), registroDTO.getSegundoApellido(), registroDTO.getTelefono(), registroDTO.getEmail(), 
				passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getFoto(),Arrays.asList(rolList.get(0)));
		
		return usuarioRepositorio.save(usuario);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public Usuario insertEstudiante(Usuario usuario) {
		List<Rol> rolList = rolRepositorio.findAll();
		usuario.setRoles(Arrays.asList(rolList.get(2)));
		return usuarioRepositorio.save(usuario);
	}
	
	@Override
	public Usuario insertProfesor(Usuario usuario) {
		List<Rol> rolList = rolRepositorio.findAll();
		usuario.setRoles(Arrays.asList(rolList.get(1)));
		return usuarioRepositorio.save(usuario);
	}
	
	@Override
	public Usuario selectUsuariobyEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findByEmail(email);
	}

	@Override
	public Usuario selectUsuariobyId(int id) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findById(id);
	}
	
	@Override
	public Usuario updateUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public void deleteUsuario(int id) {
		// TODO Auto-generated method stub
		usuarioRepositorio.deleteById(id);
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public List<Usuario> listarProfesores() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<Usuario> nueva = new ArrayList<Usuario>();
		List<Rol> rols = rolRepositorio.findAll();
		Rol rolDirector = rols.get(0);
		Rol rolProfesor = rols.get(1);
		Rol rolEstudiante = rols.get(2);
		
		for (int i = usuarios.size(); i > 0; i--) {
			if (!usuarios.get(i-1).getRoles().contains(rolEstudiante) && usuarios.get(i-1).getRoles().contains(rolProfesor) &&
					!usuarios.get(i-1).getRoles().contains(rolDirector)) {
				nueva.add(usuarios.get(i-1));
			}
		}
		return nueva;
	}
	
	@Override
	public List<Usuario> listarEstudiantes() {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<Usuario> nueva = new ArrayList<Usuario>();
		List<Rol> rols = rolRepositorio.findAll();
		Rol rolDirector = rols.get(0);
		Rol rolProfesor = rols.get(1);
		Rol rolEstudiante = rols.get(2);
		
		for (int i = usuarios.size(); i > 0; i--) {
			if (usuarios.get(i-1).getRoles().contains(rolEstudiante) && !usuarios.get(i-1).getRoles().contains(rolProfesor) &&
					!usuarios.get(i-1).getRoles().contains(rolDirector)) {
				nueva.add(usuarios.get(i-1));
			}
		}
		return nueva;
	}

}