package com.sathish.customer.model;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class EmployeeResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String empCode;
	private String name;
	private Double salary;
	private String designation;
	private String department;
	private String company;
	private String location;
	private String status;
	private String createdBy;
	private Timestamp createdDate;

}
