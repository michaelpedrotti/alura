package xyz.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xyz.entities.StoreEntity;

@Repository
public interface StoreRepository extends CrudRepository<StoreEntity, Integer> {

}
