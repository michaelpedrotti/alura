package xyz.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import xyz.jpa.repositories.CoursesRepository;
import xyz.jpa.repositories.TopicoRepository;
import xyz.modelo.Topico;
import xyz.request.TopicsRequest;

/**
 * 
 * @see https://www.baeldung.com/spring-boot-json
 */
//@Controller
//@RequestMapping("/api")
@RestController
@RequestMapping("/api/topics")
public class TopicsController {

	@Autowired
	private TopicoRepository repo;
	
	@Autowired
	private CoursesRepository repoC;
	
	
	/**
	 * 
	 * void returns HTTP code ( 200, 400, 500 )
	 * ResponseBody turns data structures into JSON and attach into body response
	 */
//	@RequestMapping(value="/topics", method=RequestMethod.POST)
	@PostMapping
	@CacheEvict(value="ukTopicFech", allEntries=true)
//	@ResponseBody
	public ResponseEntity<TopicsRequest> create(@RequestBody @Valid TopicsRequest request, UriComponentsBuilder builder){
		
		Topico entity = request.toEntity(repoC);
		
		this.repo.save(entity);
		
		
		URI uri = builder.path("/topics/{id}").buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(request);
	}	

	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicsRequest> update(@PathVariable Long id,  @RequestBody @Valid TopicsRequest request, UriComponentsBuilder builder){
		
		Topico entity = request.toEntity(repoC, id);
		
		this.repo.save(entity);
		
		return ResponseEntity.ok().body(request);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value="ukTopicFech", allEntries=true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		
		Optional<Topico> optional = this.repo.findById(id);
		
		if(optional.isEmpty()) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		this.repo.deleteById(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable("id") Long code) {
		
		Optional<Topico> optional = this.repo.findById(code);
		
		if(optional.isEmpty()) {
			
			return ResponseEntity.notFound().build();
			
		}

		return ResponseEntity.ok().body(optional.get());
	}
	
	
//	@RequestMapping(value="/topics-old", method=RequestMethod.GET)
	@GetMapping("-old")
//	@ResponseBody
	public List<Topico> fetch1(String titulo){
		
		List<Topico> list;

		if(titulo != null && !titulo.isEmpty()) {
			
//			list = this.repo.findByTitulo(titulo);
//			list = this.repo.findByCurso_Nome(titulo);
			list = this.repo.fetchByCourseName(titulo);
		}
		else {
			
			Page<Topico> page = (Page<Topico>) this.repo.findAll();
			
			list = page.getContent();
		}
		
		return list; 
	}
	
	/**
	 * 
	 * @see https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html
	 * @see https://stackoverflow.com/questions/52355490/no-primary-or-default-constructor-found-for-interface-org-springframework-data-d
	 */
	@GetMapping("/with-pageable")
	@Cacheable(value="ukTopicFech")
	public Page<Topico> fetch21(@PageableDefault(size=10) Pageable pageable){
		
		return this.repo.findAll(pageable);
	}
	
	/**
	 * requires annotation EnableSpringDataWebSupport on application startup
	 * 
	 * ?page=0&size=10&sort=id,asc&sort=dataCriacao,desc
	 */
	@GetMapping
	@Cacheable(value="ukTopicFech")
	public Page<Topico> fetch(String titulo){
				
		PageRequest pageable = PageRequest.of(0, 10);
		
		if(titulo != null && !titulo.isEmpty()) {
			
			
			return this.repo.findByTitulo(titulo, pageable);
		}
		else {
			
			return this.repo.findAll(pageable);
		}
	}
}
