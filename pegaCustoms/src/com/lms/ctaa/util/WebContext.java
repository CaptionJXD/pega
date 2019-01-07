  
package com.lms.ctaa.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WebContext {

	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
	
	private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();
	
	public static void setRequest(HttpServletRequest request) {
		requestThreadLocal.set(request);
	}
	
	public static void setResponse(HttpServletResponse response) {
		responseThreadLocal.set(response);
	}
	
	public static HttpServletRequest getRequest() {
		return requestThreadLocal.get();
	}
	
	public static HttpServletResponse getResponse() {
		return responseThreadLocal.get();
	}
	
	public static void destroy() {
		requestThreadLocal.remove();
		responseThreadLocal.remove();
	}
	
}
