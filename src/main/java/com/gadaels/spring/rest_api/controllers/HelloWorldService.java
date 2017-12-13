package com.gadaels.spring.rest_api.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/api/hello")
public class HelloWorldService {
	@RequestMapping(value="", method= RequestMethod.GET)
	public String index(){
		return "Hello world, spring rest api.";
	}
	
	@RequestMapping(value="gadaels", method= RequestMethod.GET)
	public String gadaels(@RequestParam(value="nama") String nama){
		return "Hello "+nama+".";
	}
}
