package xyz.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import xyz.entities.PositionEntity;
import xyz.repositories.PositionRepository;

@Service
public class PositionService {

	private PositionRepository repo;
	
	public PositionService(PositionRepository repo) {
		
		this.repo = repo;
	}
	
	public void insert(String name) {
		
		PositionEntity entity = new PositionEntity();
		entity.setName(name);
		
		this.repo.save(entity);
	}
	
	public void update(String name, Integer id) {
		
		Optional<PositionEntity> optional = this.repo.findById(id);
		
		if(optional.isEmpty()) {
			
			this.insert(name);
		}
		else {
			
			PositionEntity entity = optional.get();
			entity.setName(name);
			this.repo.save(entity);
		}
	}
	
	public void delete(Integer id) {
		
		Optional<PositionEntity> optional = this.repo.findById(id);
		
		if(!optional.isEmpty()) {
			
			this.delete(id);
		}
	}
}
