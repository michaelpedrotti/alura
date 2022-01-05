package xyz.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import xyz.entity.AbstractEntity;

public abstract class AbstractRepository {
	
	static private EntityManager em;
	
	public EntityManager getEm() {
		
		if(em == null) {
			
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-api");
			em = factory.createEntityManager();
		}

		return em;
	}
	
	public void beginTransaction() {
		
		this.getEm().getTransaction().begin();
	}
	
	public void commit() {
		
		this.getEm().getTransaction().commit();
	}
	
	public void rollback() {
		
		this.getEm().getTransaction().rollback();
	}
	
	public void close() {
		
		if(em != null) {
			
			//em.clear(); // DETACHED
			em.close();
			em = null;
		}
	}
	
	public void save(AbstractEntity entity) {
		
		this.getEm().persist(entity);
	}
	
	public void remove(AbstractEntity entity){
		
		entity = this.getEm().merge(entity);// Force to MANAGED
		this.getEm().remove(entity);
	}
}
