package com.zj.token.configguration;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.zj.token.interceptor.TokenInterceptor;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zj.token.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	private final static String CONTENT_TYPE = "text/html;charset=utf-8";

	@Autowired
	public TokenInterceptor tokenInterceptor;
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		System.out.println("add interceptors ");
		registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		super.configureViewResolvers(registry);
	}
	
	@Bean
	public VelocityConfigurer getVelocityConfig(){
		Properties properties = new Properties();
		properties.put("input.encoding", "UTF-8");
		properties.put("output.encoding", "UTF-8");
		properties.put("contentType", CONTENT_TYPE);
		
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
		velocityConfigurer.setResourceLoaderPath("WEB-INF/");
		velocityConfigurer.setVelocityProperties(properties);
		return velocityConfigurer;
	}
	
	
	@Bean
	public ViewResolver getViewResolver(){
		
		VelocityViewResolver velocityView = new VelocityViewResolver();
		velocityView.setSuffix(".html");
		velocityView.setAllowRequestOverride(true);
		velocityView.setAllowSessionOverride(true);
		velocityView.setExposeRequestAttributes(true);
		velocityView.setExposeSessionAttributes(true);
		velocityView.setRequestContextAttribute("rc");
		velocityView.setDateToolAttribute("dateTool");
		velocityView.setNumberToolAttribute("numberTool");
		//velocityView.setToolboxConfigLocation("WEB-INF/tools.xml”");
		velocityView.setContentType(CONTENT_TYPE);
		return velocityView;
	}
	
}
