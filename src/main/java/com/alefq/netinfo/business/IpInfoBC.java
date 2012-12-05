package com.alefq.netinfo.business;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;

import com.alefq.netinfo.domain.IpInfo;
import com.alefq.netinfo.poho.Atributo;
import com.alefq.netinfo.util.HttpUtil;
import com.alefq.netinfo.util.WebUtils;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import com.maxmind.geoip.regionName;
import com.maxmind.geoip.timeZone;

@BusinessController
public class IpInfoBC implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6102813458661843778L;
	@Inject
	private HttpUtil httpUtil;
	@Inject
	private Logger logger;
	@Inject
	WebUtils webUtils;

	public IpInfo getIpInfo(String ip) {
		IpInfo ret = new IpInfo();
		StringBuffer url = new StringBuffer(
				"http://api.hostip.info/get_json.php?position=true&ip=");
		ret.setIp(ip);
		url.append(ret.getIp());
		// String ipInfoStr = httpUtil.getResponse(url.toString());
		try {

			LookupService cl = new LookupService(
					"/usr/local/share/GeoIP/GeoLiteCity.dat",
					LookupService.GEOIP_MEMORY_CACHE);
			Location l2 = cl.getLocation(ret.getIp());
			if (l2 != null) {
				logger.debug("countryCode: "
						+ l2.countryCode
						+ "\n countryName: "
						+ l2.countryName
						+ "\n region: "
						+ l2.region
						+ "\n regionName: "
						+ regionName
								.regionNameByCode(l2.countryCode, l2.region)
						+ "\n city: "
						+ l2.city
						+ "\n postalCode: "
						+ l2.postalCode
						+ "\n latitude: "
						+ l2.latitude
						+ "\n longitude: "
						+ l2.longitude
						+ "\n metro code: "
						+ l2.metro_code
						+ "\n area code: "
						+ l2.area_code
						+ "\n timezone: "
						+ timeZone.timeZoneByCountryAndRegion(l2.countryCode,
								l2.region));

				logger.info(l2.toString());

				// JSONObject json = new JSONObject(ipInfoStr);
				// String lat = json.getString("lat");
				// String longi = json.getString("lng");
				// ret.setLatitude(Float.parseFloat(lat != "null" ? lat :
				// "1.0"));
				// ret.setLongitud(Float.parseFloat(longi != "null" ? longi :
				// "1.0"));
				// ret.setCity(json.getString("city"));
				// ret.setCountry(json.getString("country_name"));
				ret.setCountry(l2.countryName);
				ret.setCity(l2.city);
				ret.setLatitude(l2.latitude);
				ret.setLongitud(l2.longitude);
			}
			// } catch (JSONException e) {
			// logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return ret;
	}

	public List<Atributo> getListaParametros() {
		List<Atributo> ret = new ArrayList<Atributo>();
		HttpServletRequest request = webUtils.getRequest();
		if (!request.getParameterMap().isEmpty()) {
			Enumeration<String> parameters = webUtils.getRequest()
					.getParameterNames();
			while (parameters.hasMoreElements()) {
				String paramName = parameters.nextElement();
				ret.add(new Atributo(paramName, request.getParameter(paramName)));
			}
		}
		return ret;
	}

	public List<Atributo> getListaAtributos() {
		List<Atributo> ret = new ArrayList<Atributo>();
		HttpServletRequest request = webUtils.getRequest();
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String paramName = headers.nextElement();
			ret.add(new Atributo(paramName, request.getHeader(paramName)));
		}

		return ret;
	}

	public List<Atributo> getListaRequestAtributos() {
		List<Atributo> ret = new ArrayList<Atributo>();
		HttpServletRequest request = webUtils.getRequest();
		ret.add(new Atributo("nroIp.request", request.getRemoteAddr()));
		ret.add(new Atributo("characterEncoding", request
				.getCharacterEncoding()));
		ret.add(new Atributo("contentLength", String.valueOf(request
				.getContentLength())));
		ret.add(new Atributo("contentType", request.getContentType()));
		ret.add(new Atributo("protocol", request.getProtocol()));
		ret.add(new Atributo("requestURL", request.getRequestURL().toString()));
		ret.add(new Atributo("locale.country", request.getLocale().toString()));
		ret.add(new Atributo("queryString", request.getQueryString()));
		ret.add(new Atributo("requestURI", request.getRequestURI()));

		return ret;
	}
}