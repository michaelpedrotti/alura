package xyz.request;

import javax.validation.constraints.NotEmpty;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class RequestApiLogin {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String pass;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public UsernamePasswordAuthenticationToken authenticationToken() {
		
		return new UsernamePasswordAuthenticationToken(this.email, this.pass);
	}
}
