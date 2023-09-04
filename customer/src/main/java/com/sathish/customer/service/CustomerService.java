package com.sathish.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sathish.customer.entity.Customer;
import com.sathish.customer.model.EmployeeResponseDto;
import com.sathish.customer.model.RequestDto;
import com.sathish.customer.model.ResponseDto;
import com.sathish.customer.model.UpdateRequestDto;
import com.sathish.customer.repository.CustomerRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private RestTemplate restTemplate;

	public ResponseDto saveEmployee(RequestDto requestdto) {
		log.info("Entering into create customer service");
		Customer cust = objectMapper.convertValue(requestdto, Customer.class);
		customerRepo.save(cust);
		log.debug("Saved Customer Deatsils:" + cust);
		return objectMapper.convertValue(cust, ResponseDto.class);
	}

	public List<Customer> getCustomer() {
		log.info("Entering into get customer service");
		return customerRepo.findAll();
	}

	public Customer getCustByID(Long id) {
		log.info("Entering into getByID customer service");
		Optional<Customer> custbyID = customerRepo.findById(id);
		log.info("Saved Customer Deatsils:" + custbyID);
		return custbyID.isPresent() ? custbyID.get() : null;
	}

	public void delCustByID(Long id) {
		log.info("Entering into delete customer By ID service");
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

			customerRepo.save(existingCustomer);
			return objectMapper.convertValue(existingCustomer, ResponseDto.class);

		} else {
			return null;
		}
	}

	public EmployeeResponseDto getEmployeeByID(Long id) throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth("admin", "admin");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8070/api/v1/employee/" + id,
				HttpMethod.GET, entity, String.class);

		log.info("Employee Details:" + response);

		return objectMapper.readValue(response.getBody(), EmployeeResponseDto.class);

	}

}
