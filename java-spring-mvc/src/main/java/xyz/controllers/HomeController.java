package xyz.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index(HttpServletRequest request, Principal principal) {
		
		//principal.getName();
		//SecurityContextHolder.getContext().getAuthentication().getPrincipal().getName();
		
		request.setAttribute("message", "Hello World");
		
		return "home/index";
	}
}
