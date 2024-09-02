package com.mangesh.restclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.mangesh.restclient.entities.Product;

@SpringBootTest
class ProductrestclientApplicationTests {

	@Test
	public void testGetProduct() {

		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject("http://localhost:8080/productapi/products/1", Product.class);
		assertNotNull(product);
		assertEquals("IPhone",product.getName());
	}
	
	@Test
	public void testCreateProduct()
	{
		RestTemplate restTemplate=new RestTemplate();
		Product product=new Product();
		product.setName("Samsung Mobile");
		product.setDescription("Its Awesome");
		product.setPrice(1000);
		Product newProduct=restTemplate.postForObject("http://localhost:8080/productapi/products/", product,Product.class);
		
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Samsung Mobile", newProduct.getName());
	}
	
	@Test
	public void testUpdateProduct() {

		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject("http://localhost:8080/productapi/products/1", Product.class);
		product.setPrice(1400);
		restTemplate.put("http://localhost:8080/productapi/products/", product);
	}


}
