package com.alefq.netinfo.constant;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "netinfo.ipinfo")
public class IpInfoConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4805161726520310625L;
	@Name("x.forwarded.for")
	private String xForwardedFor = "x-forwarded-for";

	public String getxForwardedFor() {
		return xForwardedFor;
	}

}
