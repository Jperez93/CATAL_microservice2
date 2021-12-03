package com.secondHand.catalog.domains;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the products_states database table.
 * 
 */
@Entity
@Table(name="products_states")
@NamedQuery(name="ProductsState.findAll", query="SELECT p FROM ProductsState p")
public class ProductsState implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String state;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productsState")
	private List<Product> products;

	public ProductsState() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductsState(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductsState(null);

		return product;
	}

}