package xyz.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import xyz.entity.OrderEntity;
import xyz.entity.rows.OrderRow;

public class OrderRepository extends AbstractRepository {
	
	public void save(OrderEntity entity) {
		
		this.getEm().persist(entity);
	}
	
	public BigDecimal sum() {
		
		String pql = "SELECT SUM(o.price) FROM OrderEntity o";
		
		return this.getEm()// javax.persistence.EntityManager
			.createQuery(pql, BigDecimal.class)// javax.persistence.TypedQuery
				.getSingleResult();// java.math.BigDecimal
	}
	
	
	public List<Object[]> score() {
		
		// db is mysql needs disable sql_mode=only_full_group_by in persistence.xml
		String pql = "SELECT p.name, "
					+ "SUM(i.total), "
					+ "MAX(o.createdAt) "
					+ "FROM OrderEntity o "
					+ "JOIN o.items i "
					+ "JOIN i.product p "
					+ "GROUP BY p.name "
					+ "ORDER BY i.total DESC ";
		
		return this.getEm().createQuery(pql, Object[].class).getResultList();
	}
	
	public List<OrderRow> score2() {
		
		String pql = "SELECT new " + OrderRow.class.getName() + "( "
				+ "p.name, "
				+ "SUM(i.total), "
				+ "MAX(o.createdAt) "
				+ ") "
				+ "FROM OrderEntity o "
				+ "JOIN o.items i "
				+ "JOIN i.product p "
				+ "GROUP BY p.name "
				+ "ORDER BY i.total DESC ";
		// java.lang.String, long, java.time.LocalDateTime
		return this.getEm().createQuery(pql, OrderRow.class).getResultList();
	}
	
	public OrderEntity fetchWithClient(Long id) {
		
		// JOIN FETCH changes Lazy behavior to Eager behavior
		String pql = "SELECT o FROM OrderEntity o JOIN FETCH o.client WHERE o.id = :id ";
		
		TypedQuery<OrderEntity> query = this.getEm().createQuery(pql, OrderEntity.class);
		
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	static public OrderRepository newInstance() {
		return new OrderRepository();
	}
}
