package com.gadaels.spring.rest_api.controllers;

import java.util.List;

import com.gadaels.spring.rest_api.models.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
public class ProductService {
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@RequestMapping(value = "delete/{productid}", method = RequestMethod.DELETE)
	public Product DeleteProduct(@RequestParam int productid) {
		Product data = this.productRepository.findOne(productid);
		return data;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Product> Product() {
		List<Product> data = productRepository.findAll();
		return data;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Product AddProduct(@RequestBody Product product) {
		Product data = productRepository.save(product);
		return data;
	}

}
