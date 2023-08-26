package com.sathish.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathish.customer.entity.Customer;
import com.sathish.customer.model.RequestDto;
import com.sathish.customer.model.ResponseDto;
import com.sathish.customer.model.UpdateRequestDto;
import com.sathish.customer.model.UpdateResponseDto;
import com.sathish.customer.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/saveCustomer")
	public ResponseEntity<ResponseDto> customer(@RequestBody RequestDto requestdto) {

		try {
			ResponseDto responseDto = customerService.saveEmployee(requestdto);
			return new ResponseEntity<>(responseDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/Customer/Get")
	public ResponseEntity<List<Customer>> Getcustomer() {

		try {
			List<Customer> customerList = customerService.getCustomer();
			return new ResponseEntity<>(customerList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customer/GetID/{id}")
	public ResponseEntity<Customer> GetcustByID(@PathVariable("id") Long id) {

		try {
			Customer custByID = customerService.getCustByID(id);
			return new ResponseEntity<>(custByID, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/customer/deleteID/{id}")
	public ResponseEntity<String> DeletecustByID(@PathVariable("id") Long id) {

		try {
			customerService.delCustByID(id);
			return new ResponseEntity<>("User deleted sucessfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/customer/update/{id}")
	public ResponseEntity<ResponseDto> updateCustomer(@PathVariable long id,@RequestBody UpdateRequestDto updateRequestDto) {

		try {
			ResponseDto updatecustomer = customerService.updateCust(id,updateRequestDto);
			return new ResponseEntity<>(updatecustomer, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
