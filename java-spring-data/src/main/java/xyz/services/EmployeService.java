package xyz.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import xyz.entities.EmployeEntity;
import xyz.repositories.EmployeRepository;
import xyz.specifications.EmployeSpecification;

@Service
public class EmployeService {
	
	@Autowired
	private EmployeRepository repo;
	
	public List<EmployeEntity> report(String name) {
		
		Specification<EmployeEntity> spec = Specification.where(EmployeSpecification.name(name))
			.or(EmployeSpecification.createdAt(LocalDate.parse("2022-01-09")));
		
		return this.repo.findAll(spec);
	}
}
