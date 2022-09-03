package com.wagnerstack.entities.enums;

public enum ClientType {

	PESSOAFISICA(0, "Pessoa Física"), PESSOAJURIDICA(1, "Pessoa Física");

	Integer code;
	String description;

	private ClientType(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ClientType toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (ClientType x : ClientType.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}

		}

		throw new IllegalArgumentException("ID inválido");
	}

}
