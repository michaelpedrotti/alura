package xyz.jpa.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import xyz.modelo.Curso;

public interface CoursesRepository extends PagingAndSortingRepository<Curso, Integer> {

	public Curso findByNome(String name);
}
