package com.interviews.taller.domain;

import com.interviews.taller.processor.PaymentProcessor;

public class Payment {

	private String id;
	private Double amount;
	private String currency;
	private Status status;

	public Payment(){
		this.status = Status.PENDING;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Payment{" + "id='" + id + '\'' + '}';
	}

	public enum Status {
		PENDING, SUCCESS, FAILED
	}
}