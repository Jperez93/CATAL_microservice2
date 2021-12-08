package com.secondHand.catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.secondHand.catalog.domains.Client;

public interface ClientDAO extends CrudRepository<Client, Long>{


	public List<Client> findAll();
	public Client findByUser(String user);

	
}
