package xyz.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="order_items")
public class OrderItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="total")
	private Integer total = 1;
	
	@Column(name="price")
	private BigDecimal price;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private ProductEntity product;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderEntity order;

	public Integer getTotal() {
		return total;
	}
	
	public OrderItemEntity setTotal(Integer total) {
		this.total = total;
		return this;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public OrderItemEntity setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	public ProductEntity getProduct() {
		return product;
	}
	
	public OrderItemEntity setProduct(ProductEntity product) {
		
		System.out.println("product:" + product.getName());
		
		this.setPrice(product.getPrice());
		this.product = product;
		return this;
	}
	public OrderEntity getOrder() {
		return order;
	}
	
	public OrderItemEntity setOrder(OrderEntity order) {
		this.order = order;
		return this;
	}
	
	public OrderItemEntity fill(ProductEntity product) {
		this.setProduct(product);
		return this;
	}
		
	static public OrderItemEntity newInstance() {
		return new OrderItemEntity();
	}
}