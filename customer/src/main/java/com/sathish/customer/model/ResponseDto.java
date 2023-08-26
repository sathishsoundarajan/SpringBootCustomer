package com.sathish.customer.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String customerName;

	private String customerPhone;

	private String customerEmail;

	private String customerLocation;

	private String customerPincode;

	private String createdBy;
	private String updatedBy;

}
