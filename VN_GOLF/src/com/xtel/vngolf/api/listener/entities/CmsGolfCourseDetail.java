package com.xtel.vngolf.api.listener.entities;

import java.util.ArrayList;

public class CmsGolfCourseDetail {

	private int id;
	private String title;
	private double discount;
	private double original_price;
	private double in_week_price;
	private double weeken_price;
	private String unit;
	private String place_name;
	private String address;
	private boolean fakeOrReal;
	ArrayList<String> listImg;
	ArrayList<String> services;
	public CmsGolfCourseDetail(int id, String title, double discount, double original_price, double in_week_price,
			double weeken_price, String unit, String place_name, String address, boolean fakeOrReal,
			ArrayList<String> listImg, ArrayList<String> services) {
		super();
		this.id = id;
		this.title = title;
		this.discount = discount;
		this.original_price = original_price;
		this.in_week_price = in_week_price;
		this.weeken_price = weeken_price;
		this.unit = unit;
		this.place_name = place_name;
		this.address = address;
		this.fakeOrReal = fakeOrReal;
		this.listImg = listImg;
		this.services = services;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public double getDiscount() {
		return discount;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public double getIn_week_price() {
		return in_week_price;
	}
	public double getWeeken_price() {
		return weeken_price;
	}
	public String getUnit() {
		return unit;
	}
	public String getPlace_name() {
		return place_name;
	}
	public String getAddress() {
		return address;
	}
	public boolean isFakeOrReal() {
		return fakeOrReal;
	}
	public ArrayList<String> getListImg() {
		return listImg;
	}
	public ArrayList<String> getServices() {
		return services;
	}
	
	
}
