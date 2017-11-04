package com.mindcanary.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mindcanary.exceptions.EnumerationException;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentType {
	NONE(0, "None"), CHARGE(1, "Charge"), CASH(2, "Cash"), MOTOR_CLUB(3, "Motor Club"), PREPAID(4, "Prepaid");

	private long id;

	private String value;

	private PaymentType(long id, String value) {
		this.id = id;
		this.value = value;
	}

	public static PaymentType fromId(long id) {
		for (PaymentType paymentType : PaymentType.values()) {
			if (paymentType.getId() == id) {
				return paymentType;
			}
		}
		throw new EnumerationException(id, PaymentType.class.getName());
	}

	public static PaymentType fromValue(String value) {
		if (value == null) {
			return null;
		}
		value = value.trim();
		for (PaymentType paymentType : PaymentType.values()) {
			if (paymentType.getValue().equalsIgnoreCase(value)) {
				return paymentType;
			}
		}
		return null;
		// throw new EnumerationException(value, PaymentType.class.getName());
	}

	public long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
}
