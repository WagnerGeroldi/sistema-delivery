package com.wagnerstack.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.wagnerstack.entities.enums.StatePayment;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	private Integer id;
	
	private Integer statePayment;

	
	@OneToOne
	@JoinColumn(name= "pedido_id")
	@MapsId
	private Pedido pedido;

	public Payment() {

	}

	public Payment(Integer id, StatePayment statePayment, Pedido order) {

		this.id = id;
		this.statePayment = statePayment.getCode();
		this.pedido = order;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatePayment getStatePayment() {
		return StatePayment.toEnum(statePayment);
	}

	public void setStatePayment(StatePayment statePayment) {
		this.statePayment = statePayment.getCode();
	}

	public Pedido getOrder() {
		return pedido;
	}

	public void setOrder(Pedido order) {
		this.pedido = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

}