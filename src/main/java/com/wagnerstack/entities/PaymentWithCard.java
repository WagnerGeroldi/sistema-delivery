package com.wagnerstack.entities;

import javax.persistence.Entity;

import com.wagnerstack.entities.enums.StatePayment;

@Entity
public class PaymentWithCard extends Payment {

	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;

	public PaymentWithCard() {

	}

	public PaymentWithCard(Integer id, StatePayment statePayment, Order order, Integer numberOfInstallments) {
		super(id, statePayment, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
	

}