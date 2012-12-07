package com.alefq.netinfo.view;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

import com.alefq.netinfo.business.IpInfoBC;
import com.alefq.netinfo.domain.IpInfo;
import com.alefq.netinfo.pojo.Atributo;
import com.alefq.netinfo.util.IpUtils;
import com.alefq.netinfo.util.WebUtils;

@ViewController
@Name("ipInfoMB")
public class IpInfoMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7140401809532026772L;
	@Inject
	private IpInfoBC ipInfoBc;
	@Inject
	private WebUtils webUtils;

	public String getGoogleMapCoordinates() {
		StringBuffer ret = new StringBuffer();
		IpInfo info = getIpInfo();
		ret.append(info.getLatitude());
		ret.append(",").append(info.getLongitud());
		return ret.toString();
	}

	public IpInfo getIpInfo() {
		String ip = webUtils.getRemoteAddress(); // "201.217.28.36";//
		IpInfo info = ipInfoBc.getIpInfo(ip);
		return info;
	}

	public List<Atributo> getListaAtributos() {
		return ipInfoBc.getListaAtributos();
	}

	public List<Atributo> getListaParametros() {
		return ipInfoBc.getListaParametros();
	}

	public List<Atributo> getListaRequestAtributos() {
		return ipInfoBc.getListaRequestAtributos();
	}
}
