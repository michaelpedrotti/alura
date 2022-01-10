package xyz;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.domain.Sort;

import xyz.entities.EmployeEntity;
import xyz.projections.EmployeProjection;
import xyz.repositories.EmployeRepository;
import xyz.services.EmployeService;
import xyz.services.PositionService;

@SpringBootApplication
public class JavaSpringDataApplication implements CommandLineRunner {

//	private PositionRepository repo;
	private PositionService service;
	
	@Autowired
	private EmployeService service2;
	
	@Autowired
	private EmployeRepository repo;
	
//	public JavaSpringDataApplication(PositionRepository repo) {
	public JavaSpringDataApplication(PositionService service) {	
//		this.repo = repo;
		this.service = service;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(JavaSpringDataApplication.class, args);
	}
	
	public void insert() {
		
		System.out.println("Type the name of position from company:");
		Scanner scanner = new Scanner(System.in);
		String typed = scanner.next();
		
		this.service.insert(typed);
	}
	
	public void update() {
		
		System.out.println("Type the name of position to update:");
		Scanner scanner = new Scanner(System.in);
		String typed = scanner.next();
		
		this.service.update(typed, 2);
	}
	
	public void delete() {
		
		System.out.println("Deleting item 2 from company's position:");
		this.service.delete(2);
		
	}
	
	public void find() {
		
		String name = "Moe Howard";
		
		System.out.println("Finding by " + name);
//		
//		List<EmployeEntity> list = this.repo.findByName(name); // query-creation
		List<EmployeEntity> list = this.repo.findByName2(name);	// pql
//		List<EmployeEntity> list = this.repo.findByName3(name);// sql
//		List<EmployeEntity> list = this.repo.findByNameAndCreatedAt(name, LocalDate.parse("2022-01-10"));// query-creation
		
		if(list.size() > 0) {
			
			list.forEach((EmployeEntity entity) -> System.out.println(entity.getName()));
		}
		else {
		
			System.out.println(name + " was not found");	
		}
		
				
//		Scanner scanner = new Scanner(System.in);
//		LocalDate parsed = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	/**
	 * 
	 * @see https://www.baeldung.com/spring-data-jpa-pagination-sorting
	 */
	private void paging() {
		
		Integer page = 0;
		
		//PageRequest pageRequest = PageRequest.of(page, 5, Sort.unsorted());
		PageRequest pageRequest = PageRequest.of(page, 5, Sort.by("name").descending());
		
		
		Page<EmployeEntity> paginator = this.repo.findAll(pageRequest);
		
		System.out.println("Total: "+ paginator.getTotalElements());
		System.out.println("Current page: "+ paginator.getNumber());
		
		paginator.getContent().forEach((EmployeEntity entity) -> {
		
			System.out.println(entity);
		});
		
		
	}
	
	
	public void projections() {
		
		System.out.println("Finding all employees ");
		
		Iterable<EmployeEntity> list = this.repo.findAll();
		Iterator<EmployeEntity> iterator = list.iterator();
		
		while(iterator.hasNext()) {
			
			System.out.println(iterator.next());
		}		
	}
	
	private void specifications() {
		
		String name = "Moe";
		
		System.out.println("Finding by " + name);
		
		List<EmployeEntity> list = service2.report(name);
		
		
		if(list.size() > 0) {
			
			System.out.println("found: " + list.size());
			
			list.forEach((EmployeEntity entity) -> {
				
				System.out.println(entity);
			});
		}
		else {
			
			System.out.println("Employe with name " + name + " was not found");
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
		
//		this.insert();
//		this.update();
//		this.delete();
//		this.find();
//		this.paging();
//		this.projections();
		this.specifications();
		
//		PositionEntity entity = new PositionEntity();
//		entity.setName("Clerk");
//	
//		this.repo.save(entity);
	}
}
