package com.xtel.soapwebservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
	private int id;
	private String code;
	private String name;
	private String birthday;
	private String address;
	private String email;
	private String phone;


	@Override
	public String toString() {
		return id + "," + code + "," + name + "," + birthday + "," + address + "," + email + "," + phone + ";";
	}

}
