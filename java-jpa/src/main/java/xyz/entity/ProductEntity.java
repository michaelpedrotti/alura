package xyz.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class ProductEntity extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="create_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	private CategoryEntity category;
	
	public Long getId() {
		return id;
	}

	public ProductEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ProductEntity setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getName() {
		return name;
	}

	public ProductEntity setName(String name) {
		this.name = name;
		return this;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public ProductEntity setPrice(BigDecimal price) {
		this.price = price;
		return this;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public ProductEntity setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public CategoryEntity getCategory() {
		return this.category;
	}

	public ProductEntity setCategory(CategoryEntity category) {
		this.category = category;
		return this;
	}
	
	static public ProductEntity newInstance() {
		return new ProductEntity();
	}
}
