package xyz.controllers;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.jpa.entities.ProductsEntity;
import xyz.jpa.repositories.ProductRepository;
import xyz.request.ProductRequest;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ProductRepository repo;
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		
		return "redirect:/products";
	}
	
	@PostMapping("/save")
	public String save(@Valid ProductRequest request, BindingResult bind) {
		
		if(bind.hasErrors()) {
			
			return "products/form";
		}
				
		repo.save(request.toEntity());
		
		return "products/index"; 
	}
	
	@GetMapping("/load/{id}")
	public String load(@PathVariable("id") Integer id, ProductRequest request) {
		
		return "products/form";
	}
	
	@GetMapping("/form")
	public String form(ProductRequest request) {
		
		return "products/form";
	}
	
	
	@GetMapping("2")
	public String index2(Model model) {
		
		ProductsEntity product = new ProductsEntity();
		product.setId(150);
		product.setName("Microwave");
		product.setPrice(720.90);
		product.setDesc("Creates microwaves to cook your meal");

		List<ProductsEntity> rows = Arrays.asList(product, product, product);
		
		model.addAttribute("rows", rows);
		
		return "products/index";
	}
	
	@GetMapping("1")
	public String index1(Model model) {

		TypedQuery<ProductsEntity> query = this.em.createQuery("SELECT p from ProductsModel p", ProductsEntity.class);
		
		List<ProductsEntity> rows = query.getResultList();
		
		model.addAttribute("rows", rows);
		
		return "products/index";		
	}
	
	@GetMapping("")
	public String index(Model model) {

		Iterable<ProductsEntity> rows = this.repo.findAll();
		
		model.addAttribute("rows", rows);
		
		return "products/index";		
	}
	
	@GetMapping("/list")
	public String list(Model model) {

		PageRequest page = PageRequest.of(0, 10, Sort.by("createAt").ascending());
		
		Page<ProductsEntity> paginator = this.repo.findAll(page);
		
		model.addAttribute("rows", paginator);
		
		return "products/index";		
	}
}
