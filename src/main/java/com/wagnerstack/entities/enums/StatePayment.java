package com.wagnerstack.entities.enums;

public enum StatePayment {

	PENDING(1, "Pendente"), PAID(2, "Pago"), CANCELED(3, "Cancelado");

	Integer code;
	String description;

	private StatePayment(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static StatePayment toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (StatePayment x : StatePayment.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}

		}

		throw new IllegalArgumentException("ID inválido");
	}

}
