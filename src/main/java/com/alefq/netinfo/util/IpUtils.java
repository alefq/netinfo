package com.alefq.netinfo.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class IpUtils implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7733137541374460905L;
	public static List<String> redesPrivadas = Arrays.asList("10.", "192.168.",
			"172.16.", "172.31.", "127.0.0.");

	public boolean isPrivateNework(String ip) {
		boolean ret = false;
		if (ip != null) {
			for (String red : redesPrivadas) {
				if (ip.startsWith(red)) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
}
