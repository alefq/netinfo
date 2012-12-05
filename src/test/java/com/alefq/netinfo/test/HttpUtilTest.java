package com.alefq.netinfo.test;

import javax.inject.Inject;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;

import com.alefq.netinfo.business.IpInfoBC;
import com.alefq.netinfo.domain.IpInfo;

@RunWith(DemoiselleRunner.class)
public class HttpUtilTest extends TestCase {

	@Inject
	private IpInfoBC ipInfoBC;

	@Inject
	private Logger logger;

	@Test
	public void pruebaGetIpInfo() {
		IpInfo info = ipInfoBC.getIpInfo("200.85.32.2");
		assertNotNull(info);
		logger.debug(info.toString());
		assertNotNull(info.getLatitude());
	}
}
