package com.secondHand.catalog.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the clients database table.
 * 
 */
@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String user;

	private String city;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String name;

	private String password;

	private String surname;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="client")
	private List<Product> products;

	public Client() {
	}
	
	

	public Client(String user, String city, Date date, String name, String password, String surname) {
		super();
		this.user = user;
		this.city = city;
		this.date = date;
		this.name = name;
		this.password = password;
		this.surname = surname;
	}



	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setClient(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setClient(null);

		return product;
	}

}