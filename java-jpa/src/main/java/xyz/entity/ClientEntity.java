package xyz.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="clients")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Embedded
	private UserEntity user = new UserEntity();
	
//	@Column(name="name")
//	private String name;
	
	@Column(name="document")
	private String doc;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return this.user.getName();
	}
	public ClientEntity setName(String name) {
		this.user.setName(name);
		return this;
	}
	public String getDoc() {
		return doc;
	}
	public ClientEntity setDoc(String doc) {
		this.doc = doc;
		return this;
	}
	
	public ClientEntity fill(String name, String doc) {
		
		this.setName(name);
		this.setDoc(doc);
		
		return this;
	}
	
	static public ClientEntity newInstance() {
		return new ClientEntity();
	}
}
