package com.sathish.customer.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customerdetails")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "customer_Name")
	private String customerName;

	@Column(name = "customer_Phone")
	private String customerPhone;

	@Column(name = "customer_Email")
	private String customerEmail;

	@Column(name = "customer_Location")
	private String customerLocation;

	@Column(name = "customer_Pincode")
	private String customerPincode;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "created_by")
	private String createdBy;

}
