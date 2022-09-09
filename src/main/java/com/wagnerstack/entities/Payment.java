package com.wagnerstack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wagnerstack.entities.enums.StatePayment;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private StatePayment statePayment;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Payment() {

	}

	public Payment(Integer id, StatePayment statePayment, Pedido pedido) {
		super();
		this.id = id;
		this.statePayment = statePayment;
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatePayment getStatePayment() {
		return statePayment;
	}

	public void setStatePaymentTest(StatePayment statePayment) {
		this.statePayment = statePayment;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}