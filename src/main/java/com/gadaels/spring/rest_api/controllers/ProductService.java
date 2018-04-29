package com.gadaels.spring.rest_api.controllers;

import java.util.ArrayList;
import com.gadaels.spring.rest_api.models.*;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/product")
public class ProductService{
	ArrayList<Product> data = new ArrayList();

    public ProductService() {
        data.add(new Product(1,"PC Helpdesk"));
        data.add(new Product(2,"Laptop"));
    }

	@RequestMapping(value="", method= RequestMethod.GET)
	public ArrayList Product(){
		
		return data;
	}
    @RequestMapping(value="add", method= RequestMethod.POST)
	public ArrayList Product(@RequestBody Product product){
		data.add(product);
		return data;
	}



}
