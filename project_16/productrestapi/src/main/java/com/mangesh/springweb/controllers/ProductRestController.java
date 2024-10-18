package com.mangesh.springweb.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangesh.springweb.entities.Product;
import com.mangesh.springweb.repos.ProductRepository;


//import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Product Rest Endpoint")
//@Hidden
public class ProductRestController {

	@Autowired
	ProductRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	@RequestMapping(value = "/products/", method = RequestMethod.GET)
	//@Hidden
	public List<Product> getprProducts() {
		return repository.findAll();
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	@Operation(summary = "Returns a product",description="Takes Id returns sngle product")
	public @ApiResponse(description = "Product Object") Product getprProduct(@Parameter(description="Id of the Product") @PathVariable("id") int id)
	{
		LOGGER.info("Finding product by ID:"+id);
		return repository.findById(id).get();

	}

	@RequestMapping(value = "/products/", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product product) {

		return repository.save(product);

	}

	@RequestMapping(value = "/products/", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {

		return repository.save(product);

	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id)

	{
		repository.deleteById(id);

	}

}
