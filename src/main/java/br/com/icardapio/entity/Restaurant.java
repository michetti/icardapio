package br.com.icardapio.entity;

import java.io.Serializable;

public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private String slogan;
	
	private String phone;
	
	private String address;
	
	private String city;
	
	public Restaurant() {
		super();
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getSlogan() {
		return this.slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
}