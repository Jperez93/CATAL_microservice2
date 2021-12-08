package com.secondHand.catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.secondHand.catalog.domains.Client;
import com.secondHand.catalog.domains.Product;

public interface ProductDAO extends CrudRepository<Product, Long>{


	public List<Product> findAll();
	public List<Product> findByClient(Client email);
	public Product findByIdproducts(int idproducts);
	

	
}
