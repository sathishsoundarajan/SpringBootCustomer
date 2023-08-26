package com.sathish.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sathish.customer.entity.Customer;
import com.sathish.customer.model.RequestDto;
import com.sathish.customer.model.ResponseDto;
import com.sathish.customer.model.UpdateRequestDto;
import com.sathish.customer.repository.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ObjectMapper objectMapper;

	public ResponseDto saveEmployee(RequestDto requestdto) {

		Customer cust = objectMapper.convertValue(requestdto, Customer.class);
		customerRepo.save(cust);
		return objectMapper.convertValue(cust, ResponseDto.class);
	}

	public List<Customer> getCustomer() {
		return customerRepo.findAll();
	}

	public Customer getCustByID(Long id) {
		Optional<Customer> custbyID = customerRepo.findById(id);
		return custbyID.isPresent() ? custbyID.get() : null;
	}

	public void delCustByID(Long id) {

		customerRepo.deleteById(id);

	}

	public ResponseDto updateCust(Long id, UpdateRequestDto updateRequestDto) {

		if (customerRepo.findById(id).isPresent()) {
			Customer existingCustomer = customerRepo.findById(id).get();
			existingCustomer.setCustomerName(updateRequestDto.getCustomerName());
			existingCustomer.setCustomerPhone(updateRequestDto.getCustomerPhone());
			existingCustomer.setCustomerEmail(updateRequestDto.getCustomerEmail());
			existingCustomer.setCustomerLocation(updateRequestDto.getCustomerLocation());
			existingCustomer.setCustomerPincode(updateRequestDto.getCustomerPincode());
			existingCustomer.setCreatedBy(updateRequestDto.getCreatedBy());
			existingCustomer.setUpdatedBy(updateRequestDto.getUpdatedBy());

			// Customer updatecust = objectMapper.convertValue(updateRequestDto,
			// Customer.class);
			customerRepo.save(existingCustomer);
			return objectMapper.convertValue(existingCustomer, ResponseDto.class);

		} else {
			return null;
		}
	}
}
