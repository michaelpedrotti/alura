package xyz.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import xyz.entities.EmployeEntity;

public class EmployeSpecification {

	public static Specification<EmployeEntity> name(String name) {
		
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
	}
	
	public static Specification<EmployeEntity> createdAt(LocalDate date) {
		
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("createdAt"), date);
	}
}
