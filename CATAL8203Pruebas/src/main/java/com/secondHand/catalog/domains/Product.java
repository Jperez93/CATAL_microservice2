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

	@Lob
	private byte[] image;

	private int prize;

	private String title;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="owner")
	private Client client;

	//bi-directional many-to-one association to ProductsState
	@ManyToOne
	@JoinColumn(name="state")
	private ProductsState productsState;

	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="section")
	private Section sectionBean;

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

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getPrize() {
		return this.prize;
	}

	public void setPrize(int prize) {
		this.prize = prize;
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

	public ProductsState getProductsState() {
		return this.productsState;
	}

	public void setProductsState(ProductsState productsState) {
		this.productsState = productsState;
	}

	public Section getSectionBean() {
		return this.sectionBean;
	}

	public void setSectionBean(Section sectionBean) {
		this.sectionBean = sectionBean;
	}

}