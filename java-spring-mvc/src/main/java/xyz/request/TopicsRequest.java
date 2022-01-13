package xyz.request;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import xyz.jpa.repositories.CoursesRepository;
import xyz.modelo.Topico;
import xyz.modelo.Usuario;

public class TopicsRequest {
	
	@NotNull
	@NotEmpty
	@Length(min=5)
	private String titulo;
	
	@NotEmpty
	private String mensagem;
	
	@NotEmpty
	private String nomeCurso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getNomeCurso() {
		return nomeCurso;
	}
	
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public Topico toEntity(CoursesRepository repo) {
		
		return this.toEntity(repo, null);
	}
	
	public Topico toEntity(CoursesRepository repo, Long id) {
		
		Topico entity = new Topico();

		entity.setTitulo(this.titulo);
		entity.setMensagem(this.mensagem);
		entity.setCurso(repo.findByNome(this.nomeCurso));
		entity.setAutor(new Usuario(1L));
		
		if(id != null) {
			
			entity.setId(id);
		}
		
		return entity;
	}
}
