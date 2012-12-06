package com.alefq.netinfo.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;

import com.alefq.netinfo.constant.IpInfoConfig;

public class WebUtils {

	@Inject
	private Logger log;
	
	@Inject
	private IpInfoConfig infoConfig;

	public static WebUtils instance() {
		WeldContainer weld = new Weld().initialize();
		WebUtils webutils = weld.instance().select(WebUtils.class).get();
		return webutils;
	}

	public FacesContext getFacesContext() {

		FacesContext fc = FacesContext.getCurrentInstance();
		if (fc == null) {
			log.error("No faces context");
		}
		return fc;
	}

	public HttpServletRequest getRequest() {

		HttpServletRequest request = null;

		if (request == null) {
			if (getFacesContext() != null) {
				ExternalContext context = getFacesContext()
						.getExternalContext();
				if (context != null) {
					request = (HttpServletRequest) context.getRequest();
					if (request == null)
						log.error("No hay request disponible");
				} else {
					log.error("No hay external context disponible");
				}

			}
		}
		return request;

	}

	public String getRemoteAddress() {

		String ret = "SIN_NRO_IP";
		FacesContext fContext = getFacesContext();
		if (fContext != null) {
			if (fContext.getExternalContext() != null) {
				if (getRequest() != null)
					ret = getRequest().getRemoteAddr();
			} else {
				log.error("NO HAY ExternalContext para obtener el nro. de ip del request de HTTP");
			}
		}

		return ret;
	}

	public String getRemoteAddressXForwardedFor() {
		String ret = null;
		if(getRequest() != null)
		{
			ret = getRequest().getHeader(infoConfig.getxForwardedFor());
		}
		return ret;
	}

}