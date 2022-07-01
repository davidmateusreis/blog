package com.david.blog.components;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ConfiguracaoVisao implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/auth/user/user-contato").setViewName("/auth/user/user-contato");
		registry.addViewController("/auth/user/user-curriculo").setViewName("/auth/user/user-curriculo");
		registry.addViewController("/auth/user/user-obrigado").setViewName("/auth/user/user-obrigado");
		registry.addViewController("/auth/user/user-sobre").setViewName("/auth/user/user-sobre");
		registry.addViewController("/auth/auth-acesso-negado").setViewName("/auth/auth-acesso-negado");
	}
}