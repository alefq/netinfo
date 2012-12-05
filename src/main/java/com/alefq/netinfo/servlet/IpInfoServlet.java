package com.alefq.netinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ip", urlPatterns = "/ip")
public class IpInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1118563648873935996L;

	private boolean isPost = false;
	boolean bLastChunk = false;
	int numChunk = 0;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		isPost = true;
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
		out.println("<html><head>");
		// space for meta tags
		out.println("<title>Parámetros</title></head><body>");
		out.println("<br/>Información del Header");
		out.println("<br/>Nro. de IP: " + req.getRemoteAddr());
		out.println("<br/>Query string:"+ req.getQueryString());
		out.println("<br/>Request URI:"+ req.getRequestURI() + "<br/>");
		echoParameters(req, resp, out);
		out.println("<br/>Atributos del Header");
		printHeader(req, resp);
		out.println("</body><html>");
	}

	public void echoParameters(HttpServletRequest req,
			HttpServletResponse resp, PrintWriter out) throws ServletException,
			IOException {
		Enumeration<String> paraNames = req.getParameterNames();
		out.println(" ------------------------------<br />");
		out.println("parameters:<br />");
		if (isPost == true)
			out.println("Request Method: Post<br />");
		else
			out.println("Request Method: Get<br />");
		String pname;
		String pvalue;
		while (paraNames.hasMoreElements()) {
			pname = paraNames.nextElement();
			pvalue = req.getParameter(pname);
			out.println(pname + " = " + pvalue + "<br />");
		}
		out.println(" ------------------------------<br />");
	}

	/**
	 * Prints client header information that is available
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * 
	 */

	public void printHeader(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String headers = null;
		String htmlHeader = "<HTML><HEAD><TITLE> Request Headers</TITLE></HEAD><BODY>";
		String htmlFooter = "</BODY></HTML>";

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		Enumeration<String> e = request.getHeaderNames();

		out.println(htmlHeader);
		out.println("<TABLE ALIGN=CENTER BORDER=1>");
		out.println("<tr><th> Header </th><th> Value </th>");

		while (e.hasMoreElements()) {
			headers = e.nextElement();
			if (headers != null) {
				out.println("<tr><td align=center><b>" + headers + "</td>");
				out.println("<td align=center>" + request.getHeader(headers)
						+ "</td></tr>");
			}
		}
		out.println("</TABLE><BR/>");
		out.println(htmlFooter);

	}
}
