package xyz.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.jpa.entities.ProductsEntity;
import xyz.jpa.repositories.ProductRepository;
import xyz.request.ProductRequest;

@RestController
@RequestMapping("/api/products")
public class ProductsRestController {

	@Autowired
	private ProductRepository repo;
	
	@GetMapping("list")
	public List<ProductsEntity> list() {
		
		PageRequest page = PageRequest.of(0, 10, Sort.by("createAt").ascending());
		
		return this.repo.findAll(page).toList();
	}
	
	@PostMapping("save")
	public ProductsEntity save(@Valid @RequestBody ProductRequest request) {
		
		ProductsEntity entity = request.toEntity();
		
		this.repo.save(entity);

		return entity;
	}
}
