package xyz.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


public class OnePerRequestFilterConfig extends OncePerRequestFilter {

	private TokenServiceConfig service;
	
	public OnePerRequestFilterConfig(TokenServiceConfig service) {
		
		this.service = service;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String header = request.getHeader("Authorization");
		
		if(header != null && !header.isEmpty() && header.startsWith("Bearer ")) {
			
			String token = header.substring(7, header.length());

			if(this.service.isValid(token)) {
				
				SecurityContextHolder.getContext().setAuthentication(this.service.getAuthentication(token));
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
