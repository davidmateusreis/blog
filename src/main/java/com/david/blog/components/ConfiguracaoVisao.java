package com.david.blog.components;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ConfiguracaoVisao implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/auth/user/contato").setViewName("/auth/user/contato");
		registry.addViewController("/auth/user/curriculo").setViewName("/auth/user/curriculo");
		registry.addViewController("/auth/user/obrigado").setViewName("/auth/user/obrigado");
		registry.addViewController("/auth/user/sobre").setViewName("/auth/user/sobre");
		registry.addViewController("/auth/auth-acesso-negado").setViewName("/auth/auth-acesso-negado");
	}
}