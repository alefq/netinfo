package com.alefq.netinfo.util;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;

public class HttpUtil {

	@Inject
	Logger logger;

	public String getResponse(String url) {
		String responseBody = null;
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);

			logger.debug("executing request " + httpget.getURI());

			// Create a response handler
			BasicResponseHandler responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httpget, responseHandler);
			logger.debug(responseBody);

		} catch (ClientProtocolException e) {
			logger.error("Error de protocolo en el cliente", e);
		} catch (IOException e) {
			logger.error("Error de I/O", e);
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}
		return responseBody;
	}
}
