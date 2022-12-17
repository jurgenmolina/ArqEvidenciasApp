package com.app.proyect.Seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.proyect.Servicio.UsuarioServicio;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SeguridadAppConfiguracion extends WebSecurityConfigurerAdapter {
	
	@Lazy
	@Autowired
	private UsuarioServicio usuarioServicio;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers(
		"/registro**",
		"/js/**",
		"/css/**",
		"/img/**").permitAll()
		.antMatchers("/cursos/nuevo").hasAnyAuthority("director")
		.antMatchers("/grupos/nuevo").hasAnyAuthority("director")
		.antMatchers("/grupos/agregarEstudiantes/*").hasAnyAuthority("director")
		.antMatchers("/profesores/nuevo").hasAnyAuthority("director")
		.antMatchers("/estudiantes/nuevo").hasAnyAuthority("director")
		.antMatchers("/competencias/nuevo").hasAnyAuthority("director")
		.antMatchers("/cursos/editar/*").hasAnyAuthority("director")
		.antMatchers("/grupos/editar/*").hasAnyAuthority("director")
		.antMatchers("/profesores/editar/*").hasAnyAuthority("director")
		.antMatchers("/estudiantes/editar/*").hasAnyAuthority("director")
		.antMatchers("/competencias/editar/*").hasAnyAuthority("director")
		.antMatchers("/competencias").hasAnyAuthority("director")
		.antMatchers("/actividades/*").hasAnyAuthority("profesor","estudiante") //cambiar profesor
	    .anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll()
		.and().exceptionHandling().accessDeniedPage("/error");
		
		
//		http.authorizeRequests().antMatchers(
//				"/registro**",
//				"/js/**",
//				"/css/**",
//				"/img/**").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.and()
//		.logout()
//		.invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout")
//		.permitAll();
		
//		http.authorizeRequests().antMatchers(
//				"/registro**",
//				"/js/**",
//				"/css/**",
//				"/img/**").permitAll()
//	    .antMatchers("/admin/*").hasRole("director")
//	    .anyRequest().authenticated()
//	    .and()
//	    .formLogin()
//	        .loginPage("/login")
//	        .permitAll()
//	    .and()
//	    .logout()
//	    .invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout")
//	    .permitAll();
		
//		http.authorizeRequests()
//	    .antMatchers("/").permitAll()
//	    .antMatchers("/admin/*").hasRole("director")
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.and()
//		.logout()
//		.invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout")
//		.permitAll();
		
	}
	
	
	
	

}
