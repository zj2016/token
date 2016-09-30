package com.zj.token.configguration;



import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;

import com.zj.token.interceptor.TokenInterceptor;


@Configuration
public class AppConfig implements EnvironmentAware{

	@Bean
	public HandlerInterceptor getTokenInterceptor(){
		return new TokenInterceptor();
	}

	public void setEnvironment(Environment arg0) {
		
	}
	
	
}
