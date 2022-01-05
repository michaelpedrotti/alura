package xyz.repository;

import xyz.entity.OrderEntity;

public class OrderRepository extends AbstractRepository {
	
	public void save(OrderEntity entity) {
		
		this.getEm().persist(entity);
	}

	static public OrderRepository newInstance() {
		return new OrderRepository();
	}
}
