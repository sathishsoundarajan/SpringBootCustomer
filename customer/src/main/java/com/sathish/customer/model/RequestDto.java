package com.sathish.customer.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerName;

	private String customerPhone;

	private String customerEmail;

	private String customerLocation;

	private String customerPincode;

	private String createdBy;

}
