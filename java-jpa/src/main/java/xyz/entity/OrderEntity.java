package xyz.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="create_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="client_id")
	private ClientEntity client;
	
	@Column(name="price")
	private BigDecimal price = new BigDecimal(0.00);
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private List<OrderItemEntity> items = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public OrderEntity setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public ClientEntity getClient() {
		return client;
	}

	public OrderEntity setClient(ClientEntity client) {
		this.client = client;
		return this;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public OrderEntity setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public OrderEntity addItem(OrderItemEntity entity){
		
		entity.setOrder(this);
		this.items.add(entity);

//		this.setPrice(this.getPrice() + entity.getPrice());
		
		this.setPrice(new BigDecimal(200.00));
		
		return this;
	}
	
	static public OrderEntity newInstance(){
		return new OrderEntity();
	}
}
