package com.zj.token.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zj.token.annotation.Token;

public class TokenInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("token interceptor .......  ");
		
		if(handler instanceof HandlerMethod){
			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			Token token = handlerMethod.getMethodAnnotation(Token.class);
			
			if(token != null){
				boolean saveToken = token.save();
				if(saveToken){
					request.getSession().setAttribute("token", "ZJ"+UUID.randomUUID().toString());
				}
				
				boolean removeToken = token.remove();
				if(removeToken){
					if(isRepeatSubmit(request)){
						System.out.println("正在处理。。。。。不要重复提交");
						return false;
					}
					request.getSession().removeAttribute("token");
				}
			}
			return true;
		}		
		return super.preHandle(request, response, handler);
	}
	
	
	private boolean isRepeatSubmit(HttpServletRequest request){
		
		String token = (String) request.getSession().getAttribute("token");
		
		if(token == null){
			return true;
		}
		
		String clientToken = request.getParameter("clientToken");

		if(clientToken == null){
			return true;
		}
		
		if(!token.equals(clientToken)){
			return true;
		}
		
		return false;
		
	}

}
