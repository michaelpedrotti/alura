package xyz.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserEntity {
	
	@Column(name="name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
