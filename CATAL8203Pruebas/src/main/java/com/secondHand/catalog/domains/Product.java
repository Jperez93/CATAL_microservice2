package com.secondHand.catalog.domains;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idproducts;

	private String description;

	private String image;

	private int prize;

	private String section;

	private String state;

	private String title;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="owner")
	private Client client;

	public Product() {
	}

	public int getIdproducts() {
		return this.idproducts;
	}

	public void setIdproducts(int idproducts) {
		this.idproducts = idproducts;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrize() {
		return this.prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}