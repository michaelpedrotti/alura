package xyz.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@Profile("development")
public class DevelopmentWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private TokenServiceConfig token;
	
	@Autowired
	private UserDetailsConfig service;
	
	/**
	 * Authorization
	 * 
	 * @see https://spring.io/guides/gs/securing-web/
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		 http.authorizeRequests()
		 	.antMatchers("/**").permitAll()
		 	.and().csrf().disable();	
	}
	
	/**
	 * Authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		builder.userDetailsService(this.service)
			.passwordEncoder(encoder);
		
		

//		
//		// Creating new user for start
//		UserDetails user = User.builder()
//				.username("admin")
//				.password(encoder.encode("admin"))
//				.roles("ADM")
//				.build();
//		
//		builder.jdbcAuthentication()
//			.dataSource(this.dataSource)
//			.passwordEncoder(encoder);
//			.withUser(user);
	}

	/**
	 * Static Resource
	 */	
	@Override
	public void configure(WebSecurity web) throws Exception {

		super.configure(web);
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails user = User.withDefaultPasswordEncoder()
//			.username("admin")
//			.password("admin")
//			.roles("ADM")
//			.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
}
