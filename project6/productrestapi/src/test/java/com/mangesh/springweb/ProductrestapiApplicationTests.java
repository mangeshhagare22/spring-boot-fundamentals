package com.mangesh.springweb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.mangesh.springweb.entities.Product;





@SpringBootTest
class ProductrestapiApplicationTests {
	
	@Value("${productrestapi.services.url}")
	private String baseURL;

	@Test
	public void testGetProduct() {
		
		System.out.println(baseURL);

		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"1", Product.class);
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
		Product newProduct=restTemplate.postForObject(baseURL, product,Product.class);
		
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Samsung Mobile", newProduct.getName());
	}
	
	@Test
	public void testUpdateProduct() {

		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL+"1", Product.class);
		product.setPrice(1400);
		restTemplate.put(baseURL, product);
	}

}
