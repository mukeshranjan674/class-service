package com.example.category.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.category.model.Name;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@PostMapping("/full-name")
	public ResponseEntity<String> getFullName(@RequestHeader(value = "X-Trace-Id", required = false) String traceId,
			@RequestBody(required = true) Name name){
		
		if(traceId == null) {
			traceId = UUID.randomUUID().toString();
		}
		
		logger.info("Trace id : {} | API : {} | Payload : {} ", traceId, "POST /full-name ", name.toString()); 
		
		String fullname = String.join(" ", name.getName(), name.getSurname());
		
		return new ResponseEntity<>(fullname, HttpStatus.CREATED);
	}


}
