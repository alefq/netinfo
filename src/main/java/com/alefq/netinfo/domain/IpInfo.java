package com.alefq.netinfo.domain;

import java.util.List;

public class IpInfo {

	private String country;
	private String city;
	private String ip;
	private Float latitude;
	private Float longitud;
	private List<String> ipForwardedFor;
	private Boolean forwardingPrivateNetwork;
	private Boolean privateNetwork;

	public IpInfo() {
		// TODO Auto-generated constructor stub
	}

	public IpInfo(String ip) {
		setIp(ip);
	}

	public String getCountry() {
		if (country == null || "null".equals(country))
			return "no disponible";
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		if (city == null || "null".equals(city))
			return "no disponible";
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

	public List<String> getIpForwardedFor() {
		return ipForwardedFor;
	}

	public void setIpForwardedFor(List<String> ipForwardedFor) {
		this.ipForwardedFor = ipForwardedFor;
	}

	public Boolean getForwardingPrivateNetwork() {
		if(forwardingPrivateNetwork == null)
			forwardingPrivateNetwork = Boolean.FALSE;
		return forwardingPrivateNetwork;
	}

	public void setForwardingPrivateNetwork(Boolean forwardingPrivateNetwork) {
		this.forwardingPrivateNetwork = forwardingPrivateNetwork;
	}

	public Boolean getPrivateNetwork() {
		return privateNetwork;
	}

	public void setPrivateNetwork(Boolean privateNetwork) {
		this.privateNetwork = privateNetwork;
	}

}
