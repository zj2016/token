package com.zj.token.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zj.token.annotation.Token;

@Controller
@RequestMapping("/token")
public class TokenFormController {

	@Token(remove = true)
	@RequestMapping("/test1")
	public String tokenTest1(HttpServletRequest request,String clientToken){
		
		String token = (String) request.getSession().getAttribute("token");
		
		System.out.println("token1 ----  " + token);
		System.out.println("ctoken1 ---- " + clientToken);
		
		return "index";
	}
	
	@Token(save = true)
	@RequestMapping("/index")
	public String token(HttpServletRequest request, ModelMap modelMap){
		
		String token = (String) request.getSession().getAttribute("token");
		
		System.out.println("token ----  " + token);
		
		modelMap.addAttribute("token", token);
		
		return "index";
	}
	
	@Token(remove = true)
	@RequestMapping("/test2")
	public String tokenTest2(HttpServletRequest request, String clientToken){
		
		String token = (String) request.getSession().getAttribute("token");
		
		System.out.println("token2 ----  " + token);
		System.out.println("ctoken2 ----  " + clientToken);
		
		return "index";
	}
	
	
	public static void main(String[] args){
		//SpringApplication.run(TokenFormController.class, args);
	}
	
}
