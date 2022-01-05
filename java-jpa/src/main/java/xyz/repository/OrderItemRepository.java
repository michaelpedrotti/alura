package xyz.repository;

import xyz.entity.OrderItemEntity;

public class OrderItemRepository extends AbstractRepository {
	
	public void save(OrderItemEntity entity) {
		
		this.getEm().persist(entity);
	}

	static public OrderItemRepository newInstance() {
		return new OrderItemRepository();
	}
}
