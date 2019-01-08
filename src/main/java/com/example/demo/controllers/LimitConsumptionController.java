/**
 * 
 */
package com.example.demo.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Transaction;
import com.example.demo.service.DBService;
import com.example.demo.service.LimitRepository;

/**
 * @author Abhijeet Gupta
 *
 */
@RestController
public class LimitConsumptionController {

	@Autowired
	DBService dbService;
	
	@Autowired
	LimitRepository noteRepository;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping("/transactions")
	public ResponseEntity<HttpStatus> doLimitConsumptionCheck(@RequestBody Transaction transaction) {
		ResponseEntity<HttpStatus> responseEntity = null;
		// validate the request
		int res = dbService.update(1, "AAA");
		System.out.println(res);
		if (res >= 0)
			responseEntity = new ResponseEntity<HttpStatus>(HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		return responseEntity;

	}

}
