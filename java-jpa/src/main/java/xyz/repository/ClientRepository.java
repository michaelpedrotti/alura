package xyz.repository;

import xyz.entity.ClientEntity;

public class ClientRepository extends AbstractRepository {
	
	public ClientEntity findOrNew(Long id) {
		
		ClientEntity row = this.getEm().find(ClientEntity.class, id);
		
		if(row == null) {
			
			row = ClientEntity.newInstance();
			row.setId(id);
		}
		
		return row;
	}
	
	public void save(ClientEntity entity) {
		
		entity = this.getEm().merge(entity);
		this.getEm().persist(entity);
	}
	
	static public ClientRepository newInstance() {
		return new ClientRepository();
	}
}
