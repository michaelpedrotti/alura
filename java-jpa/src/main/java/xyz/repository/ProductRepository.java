package xyz.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import xyz.entity.ProductEntity;

public class ProductRepository extends AbstractRepository {
	

	public void save(ProductEntity entity) {
		
		this.getEm().persist(entity);
	}
	
	public void remove(ProductEntity entity){
		
		entity = this.getEm().merge(entity);// Force to MANAGED
		this.getEm().remove(entity);
	}
	
	public ProductEntity findOrNew(Long id) {
		
		ProductEntity row = this.getEm().find(ProductEntity.class, id);
		
		if(row == null) {
			
			row = ProductEntity.newInstance();
			row.setId(id);
			
			this.getEm().flush();
		}
		
		return row;
	}
	
	public List<ProductEntity> all(){
		
		String pql = String.format("SELECT p FROM %s p", ProductEntity.class.getName());
		return this.getEm().createQuery(pql, ProductEntity.class).getResultList();
	}
	
	public BigDecimal findPriceById(Long id) {
		
		String pql = String.format("SELECT p.price FROM %s p WHERE p.id = :id", ProductEntity.class.getName());
		
		TypedQuery<BigDecimal> query = this.getEm().createQuery(pql, BigDecimal.class);
		query.setParameter("id", id);
		
		return query.getSingleResult();
	}
	
	public List<ProductEntity> fetchCategoryName(String name){
		
		String pql = String.format("SELECT p FROM %s p WHERE p.category.name = :name", ProductEntity.class.getName());
		
		TypedQuery<ProductEntity> query = this.getEm().createQuery(pql, ProductEntity.class);
		
		query.setParameter("name", name);
		
		return query.getResultList();
	}
	
	/**
	 * with NamedQuery on ProductEntity
	 */
	public List<ProductEntity> fetchCategoryName2(String name){

		TypedQuery<ProductEntity> query = this.getEm().createNamedQuery("productByCategory", ProductEntity.class);
		
		query.setParameter("name", name);
		
		return query.getResultList();
	}
	
	static public ProductRepository newInstance() {
		return new ProductRepository();
	}
}
