package xyz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xyz.entities.PositionEntity;

@Repository
public interface PositionRepository extends CrudRepository<PositionEntity, Integer> {

	
}
