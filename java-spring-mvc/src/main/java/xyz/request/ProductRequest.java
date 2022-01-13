package xyz.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.google.gson.Gson;

import xyz.jpa.entities.ProductsEntity;

public class ProductRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;
	
	@Pattern(regexp = "^\\d+(\\.[\\d]{2})?")// 127.90
	@NotBlank
	private String price;
	
	@NotBlank
	private String desc;

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getDesc() {
		return desc;
	}

	public ProductsEntity toEntity() {
		
		ProductsEntity entity = new ProductsEntity();
		entity.setName(this.name);
		entity.setDesc(this.desc);
		entity.setPrice(new Double(this.price));
		
		return entity;
	}
	
	@Override
	public String toString() {
		
		return (new Gson()).toJson(this);
	}
}
