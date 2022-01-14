package xyz.configs;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.jpa.repositories.UsuarioRepository;
import xyz.modelo.Usuario;

@Service
public class UserDetailsConfig implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> optional = this.repo.findByEmail(username);
		
		if(optional.isEmpty()) {
			
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		
		return optional.get();
	}
}