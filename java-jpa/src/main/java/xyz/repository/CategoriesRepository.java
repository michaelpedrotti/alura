package xyz.repository;

import java.util.List;

import xyz.entity.CategoryEntity;

public class CategoriesRepository extends AbstractRepository {
	
	private Class<CategoryEntity> entityClass = CategoryEntity.class;
	
	public CategoryEntity findOrNew(Long id) {
		
		CategoryEntity row = this.getEm().find(entityClass, id);
		
		if(row == null) {
			
			row = CategoryEntity.newInstance();
			row.setId(id);
		}
		
		return row;
	}
	
	public void save(CategoryEntity entity) {
		
		this.getEm().persist(entity);
	}
	
	public List<CategoryEntity> all(){
		
		return this.getEm().createQuery("SELECT c FROM categories c", CategoryEntity.class).getResultList();
	}
	
	static public CategoriesRepository newInstance() {
		return new CategoriesRepository();
	}
}
