package xyz.jpa.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import xyz.jpa.entities.ProductsEntity;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductsEntity, Integer> {

	@Cacheable("products")
	@Override Iterable<ProductsEntity> findAll();
}