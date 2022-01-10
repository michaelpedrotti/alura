package xyz.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import xyz.entities.EmployeEntity;
import xyz.projections.EmployeProjection;

@Repository
public interface EmployeRepository extends PagingAndSortingRepository<EmployeEntity, Integer>, JpaSpecificationExecutor<EmployeEntity> {
//public interface EmployeRepository extends CrudRepository<EmployeEntity, Integer> {

	/**
	 * Query Creation
	 * 
	 * @see https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	 */
	List<EmployeEntity> findByName(String name);

	/**
	 * Make the same using PQL
	 */
	@Query("SELECT e FROM EmployeEntity e WHERE e.name = :name")
	List<EmployeEntity> findByName2(String name);
	
	/**
	 * Make the same using SQL
	 */
	@Query(value="SELECT * FROM employees WHERE name = :name", nativeQuery=true)
	List<EmployeEntity> findByName3(String name);	
	
	List<EmployeEntity> findByNameAndCreatedAt(String name, LocalDate createAt);
	
	/**
	 * Spring Data JPA Projections
	 * 
	 * @see https://www.baeldung.com/spring-data-jpa-projections
	 */
	@Query(value="SELECT * FROM employees", nativeQuery=true)
	List<EmployeProjection> findAll(String name);
}
