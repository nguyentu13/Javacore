package com.xtel.webservice.entity;

public class Schedule {
	private int id;
	private String code;
	private String name;
	private String birthday;
	private String address;
	private String email;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Schedule() {
		super();
	}

	public Schedule(int id, String code, String name, String birthday, String address, String email, String phone) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return id + "," + code + "," + name + "," + birthday + "," + address + "," + email + "," + phone + ";";
	}

}
