package com.secondHand.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.secondHand.catalog.domains.Client;
import com.secondHand.catalog.domains.Product;
import com.secondHand.catalog.repository.ImagesManager;
import com.secondHand.catalog.repository.ProductDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;

@Controller
@RequestMapping("/productos")
public class ProductsController {
	
	private final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ProductsController.class);
	
	
	@Autowired
	private ProductDAO daoproduct;
	
	@Autowired
	private ImagesManager imagesManager;
	
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("PRODUCTS_LIST", daoproduct.findAll());
		return "clients/User";
	}
	
	
	@GetMapping("/create")
	public String create() {
		return "clients/add";
	}
	
	@PostMapping("/save")
	public String save(Product product, @RequestParam("img") MultipartFile file) throws IOException {
		LOGGER.info("este es el objeto file {}", file);
		Client c = new Client("kalask@gmail.com","",null,"","","");
		product.setClient(c);
		//images
		if(product.getIdproducts() == 0) {
			String imageName = imagesManager.saveImage(file);
			product.setImage(imageName);
		}else {
			
		}
		daoproduct.save(product);
		return "redirect:/productos";
	}
	
	@GetMapping("/profile")
	public String showProfile(Model model) {
		Client c = new Client("kalask@gmail.com","",null,"","","");
		model.addAttribute("CLIENTS_PRODUCTS_LIST", daoproduct.findByClient(c));
		return "clients/profile";
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable Integer id, Model model) {
		Product product = daoproduct.findByIdproducts(id);
		model.addAttribute("PRODUCT", product);
		LOGGER.info("Producto buscado: {}", product);
		return "clients/details";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable Integer id, Model model) {
		Product product = daoproduct.findByIdproducts(id);
		model.addAttribute("PRODUCT", product);
		LOGGER.info("Producto buscado: {}", product);
		return "clients/modify";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		LOGGER.info("Producto borrado: {}", daoproduct.findByIdproducts(id));
		Product p = new Product();
		p=daoproduct.findByIdproducts(id);
		if(!p.getImage().equals("default.png")) {
			imagesManager.deleteImage(p.getImage());
		}
		daoproduct.delete(p);
		return "redirect:/productos/profile";
	}
	@PostMapping("/search")
	public String search(@RequestParam String title, String description, String city, String section, String user, Model model
) {
		List<Product> allProducts = daoproduct.findAll();
		List<Product> filteredProducts = new ArrayList<Product>();
		String message = "";
		boolean notFound = false;
		for (int i = 0; i < allProducts.size(); i++) {
			Product p = allProducts.get(i);
			boolean coincident = false;
			if(!title.isBlank()) {
				coincident = p.getTitle().contains(title);
				notFound = true;
			}
			if(!description.isBlank()) {
				coincident = p.getDescription().contains(description);
				notFound = true;
			}
			if(!section.isBlank()) {
				coincident = p.getSection().contains(section);
				notFound = true;

			}
			if(!user.isBlank()) {
				Client owner = p.getClient();
				coincident = owner.getName().concat(owner.getSurname()).contains(user);
				notFound = true;

			}
			if(!city.isBlank()) {
				coincident = p.getClient().getCity().contains(city);
				notFound = true;

			}
			if(coincident) {
				filteredProducts.add(p);
			}
			
		}
		if(filteredProducts.isEmpty()) {
			model.addAttribute("PRODUCTS_LIST", allProducts);
			LOGGER.info("enviando todo "+title);
			if(notFound) {
				message = "No se ha encontrado ninguna coincidencia";
				LOGGER.info("enviando mensaje");
			}
		}else {
			model.addAttribute("PRODUCTS_LIST", filteredProducts);
			LOGGER.info("enviando todo "+title);

		}
		model.addAttribute("MESSAGE", message);
		return "clients/User";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(Product product, @PathVariable Integer id, @RequestParam("img") MultipartFile file, Model model) throws IOException {
		Product productToReplace = daoproduct.findByIdproducts(id);
		Client client = productToReplace.getClient();
		product.setClient(client);
		product.setIdproducts(productToReplace.getIdproducts());
		if(file.isEmpty()) { //edit the product without changing the image
			product.setImage(productToReplace.getImage());
		}else {
			String imageName = imagesManager.saveImage(file);
			if(!productToReplace.getImage().equals("default.png")) {
				imagesManager.deleteImage(productToReplace.getImage()); //If is modifying the image, delete the previous one from the server
			}
			product.setImage(imageName);
			
			
		}
		daoproduct.delete(productToReplace);
		daoproduct.save(product);
		LOGGER.info("Producto borrado: {}", productToReplace);
		LOGGER.info("Producto insertado: {}", product);
		return "redirect:/productos/profile";
	}
	
	
	
	

}
