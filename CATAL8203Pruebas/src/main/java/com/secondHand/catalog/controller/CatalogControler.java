package com.secondHand.catalog.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.secondHand.catalog.domains.Client;
import com.secondHand.catalog.domains.Product;
import com.secondHand.catalog.repository.ClientDAO;
import com.secondHand.catalog.repository.ProductDAO;

@Controller
//@CrossOrigin
public class CatalogControler {

	@Autowired
	ProductDAO daoproduct;
	@Autowired
	ClientDAO daoclient;

	@Autowired
	RestTemplate restTemplate;

	/******************
	 * OPERATIONS ON USERS
	 ******************************************/


	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Product>> getProducts(
			@RequestParam(value = "email", required = false) String email) {
		List<Product> productsList;
		if (email == null) {
			productsList = daoproduct.findAll();
		} else {
			Client client = daoclient.findByUser(email);
			productsList = daoproduct.findByClient(client);
		}
		return new ResponseEntity<List<Product>>(productsList, HttpStatus.OK);
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Client>> getClients() {
		List<Client> clientList;
		clientList = daoclient.findAll();
		return new ResponseEntity<List<Client>>(clientList, HttpStatus.OK);
	}

}
