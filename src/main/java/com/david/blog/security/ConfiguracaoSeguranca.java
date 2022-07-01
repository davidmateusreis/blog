package com.david.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.david.blog.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private SucessLogin sucessLogin;
	
	@Bean
	public BCryptPasswordEncoder gerarCriptografia() {
		BCryptPasswordEncoder criptografia = new BCryptPasswordEncoder();
		return criptografia;
	}
	
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		DetalhesUsuarioServico detalhesDoUsuario = new DetalhesUsuarioServico(usuarioRepository);
		return detalhesDoUsuario;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/auth/user/*").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/auth/admin/*").hasAnyAuthority("ADMIN")
		.antMatchers("/usuario/admin/*").hasAnyAuthority("ADMIN")
		.antMatchers("/jogo/admin/*").hasAnyAuthority("ADMIN")
		.and()
		.exceptionHandling().accessDeniedPage("/auth/auth-acesso-negado")
		.and()
		.formLogin().successHandler(sucessLogin)
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/").permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserDetailsService detalhesDoUsuario = userDetailsServiceBean();
		
		BCryptPasswordEncoder criptografia = gerarCriptografia();
		
		auth.userDetailsService(detalhesDoUsuario).passwordEncoder(criptografia);
	}
}