package com.github.restfuljavagae;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello Admin!! " +
				"Sorry, We are working on Admin page.");
		
	}
}
