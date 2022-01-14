package xyz.configs;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import xyz.jpa.repositories.UsuarioRepository;
import xyz.modelo.Usuario;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class TokenServiceConfig {

	@Value("${api.jwt.expiration}")
	private String expiration;
	
	@Value("${api.jwt.secret}")
	private String secret;
	
	@Autowired
	private UsuarioRepository repo;
	
	public String generateToken(Authentication auth) {
		
		Date today = new Date();
		Usuario user = (Usuario)auth.getPrincipal();
		
		System.out.println(this.expiration);
		
		return Jwts.builder()
			.setIssuer("xyz")
			.setSubject(user.getId().toString())
			.setIssuedAt(today)
			.setExpiration(new Date(today.getTime() + Long.parseLong(this.expiration) ))
			.signWith(SignatureAlgorithm.HS256, this.secret)
			.compact();
	}
	
	public Boolean isValid(String token) {
		
		Boolean bool = true;
		
		try {
		
			Jwts.parser()
				.setSigningKey(this.secret)
				.parseClaimsJws(token);
		}
		catch (Exception e) {
			bool = false;
		}
		
		return bool;
	}

	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		
		String subject = Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token)
				.getBody()
					.getSubject();

		Usuario user = this.repo.findById(Long.parseLong(subject)).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		return authentication;
	}
}
