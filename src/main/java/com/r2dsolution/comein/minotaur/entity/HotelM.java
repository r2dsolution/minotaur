package com.r2dsolution.comein.minotaur.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;




@Entity(name="hotel_info")
public class HotelM implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	private String hotelName;
	
	private String address;
	private String country;
	private String province;
	
	@Column(name="image1_url")
	private String image1Url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getImage1Url() {
		return image1Url;
	}

	public void setImage1Url(String image1Url) {
		this.image1Url = image1Url;
	}
	
	

}
