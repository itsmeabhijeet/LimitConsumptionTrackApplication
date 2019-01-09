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

/**
 * @author Abhijeet Gupta
 *
 */
@RestController
public class LimitConsumptionController {

	@Autowired
	DBService dbService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping("/transactions")
	public ResponseEntity<String> doLimitConsumptionCheck(@RequestBody Transaction transaction) {
		ResponseEntity<String> responseEntity = null;
		int res = dbService.updateLimitValues(transaction);
		if (res == 1)
			responseEntity = new ResponseEntity<>("Transaction Success", HttpStatus.OK);
		else
			responseEntity = new ResponseEntity<>("Transaction Failed as limit is over", HttpStatus.BAD_REQUEST);
		return responseEntity;

	}

}
