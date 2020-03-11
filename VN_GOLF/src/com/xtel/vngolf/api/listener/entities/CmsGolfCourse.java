package com.xtel.vngolf.api.listener.entities;

public class CmsGolfCourse {
	private int id;
	private String name;
	private String avatar_url;
	private String title;
	private String place;
	private double discount;
	private double original_price;
	private double in_week_price;
	private double weekend_price;
	private int language_id;
	private String currency_unit;
	private String description;
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
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	public double getIn_week_price() {
		return in_week_price;
	}
	public void setIn_week_price(double in_week_price) {
		this.in_week_price = in_week_price;
	}
	public double getWeekend_price() {
		return weekend_price;
	}
	public void setWeekend_price(double weekend_price) {
		this.weekend_price = weekend_price;
	}
	public int getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}
	public String getCurrency_unit() {
		return currency_unit;
	}
	public void setCurrency_unit(String currency_unit) {
		this.currency_unit = currency_unit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public CmsGolfCourse(int id, String name, String avatar_url, String title, String place, double discount,
			double original_price, double in_week_price, double weekend_price, int language_id, String currency_unit,
			String description) {
		super();
		this.id = id;
		this.name = name;
		this.avatar_url = avatar_url;
		this.title = title;
		this.place = place;
		this.discount = discount;
		this.original_price = original_price;
		this.in_week_price = in_week_price;
		this.weekend_price = weekend_price;
		this.language_id = language_id;
		this.currency_unit = currency_unit;
		this.description = description;
	}
	public CmsGolfCourse() {
		super();
	}
	@Override
	public String toString() {
		return "CmsGolfCourse [id=" + id + ", name=" + name + ", avatar_url=" + avatar_url + ", title=" + title
				+ ", place=" + place + ", original_price=" + original_price + ", in_week_price=" + in_week_price
				+ ", weekend_price=" + weekend_price + ", language_id=" + language_id + ", currency_unit="
				+ currency_unit + ", description=" + description + "]";
	}
	
	
}
