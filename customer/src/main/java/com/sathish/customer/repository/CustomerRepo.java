package com.sathish.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathish.customer.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
