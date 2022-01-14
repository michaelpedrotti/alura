package xyz.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.configs.TokenServiceConfig;
import xyz.request.RequestApiLogin;

@Controller
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenServiceConfig service;
	
	
	@GetMapping("/login")
	public String login() {
	
		return "auth/login";
	}
	
	
	@PostMapping("/api/login")
	@ResponseBody
	public ResponseEntity<?> auth(@RequestBody @Valid RequestApiLogin request) {

//		System.out.println(request.getEmail());
//		System.out.println(request.getPass());
		
		try {
			
			Authentication auth = this.authManager.authenticate(request.authenticationToken());
			
			String token = service.generateToken(auth);
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("token", token);
			map.put("type", "Bearer");
				
			return ResponseEntity.ok(map);
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.badRequest().build();
		}
	}
}
