package com.secondHand.catalog.controller;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestController;

import com.secondHand.catalog.domains.Client;
import com.secondHand.catalog.repository.ClientDAO;

@RestController
@CrossOrigin
public class CatalogControler {

	@Autowired
	ClientDAO daoclient;

	/******************
	 * OPERATIONS ON USERS
	 ******************************************/

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Client>> getClients(
			@RequestParam(value = "email", required = false) String email) {
		List<Client> clientsList;
		clientsList = daoclient.findAll();
		return new ResponseEntity<List<Client>>(clientsList, HttpStatus.OK);
	}

}
