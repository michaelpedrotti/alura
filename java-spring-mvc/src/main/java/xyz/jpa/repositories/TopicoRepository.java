package xyz.jpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import xyz.modelo.Topico;

@Repository
public interface TopicoRepository extends PagingAndSortingRepository<Topico, Long> {

	List<Topico> findByTitulo(String titulo);
	
	Page<Topico> findByTitulo(String titulo, PageRequest page);
	
	List<Topico> findByCurso_Nome(String name);
	
	@Query("SELECT t From Topico t WHERE t.curso.nome = :nomeCurso")
	List<Topico> fetchByCourseName(@Param("nomeCurso") String name);
}
