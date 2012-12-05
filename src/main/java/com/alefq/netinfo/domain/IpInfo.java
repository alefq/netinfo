package com.alefq.netinfo.domain;

public class IpInfo {

	private String country;
	private String city;
	private String ip;
	private Float latitude;
	private Float longitud;

	public IpInfo() {
		// TODO Auto-generated constructor stub
	}

	public IpInfo(String ip) {
		setIp(ip);
	}

	public String getCountry() {
		if (country == null || "null".equals(country))
			return "";
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		if (city == null || "null".equals(city))
			return "";
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
}
