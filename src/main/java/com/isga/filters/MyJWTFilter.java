package com.isga.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

import com.google.gson.Gson;
import com.isga.model.ErrorAPIResponse;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet Filter implementation class MyJWTFilter
 */
@WebFilter({"/api/restaurant/s/*"})
public class MyJWTFilter extends HttpFilter implements Filter {
	private static final String AUTHENTICATION_SCHEME = "Bearer ";
	@EJB
	private ISecurityBeanLocal securityBean;
    public MyJWTFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		resp.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		resp.setHeader("Access-Control-Max-Age", "1209600");
		Collections.list(req.getHeaderNames()).stream().collect(Collectors.toMap(h -> h, h -> {
		    ArrayList<String> headerValues = Collections.list(req.getHeaders(h));
		    return headerValues.size() == 1 ? headerValues.get(0) : headerValues;
		})).forEach((h,s)->{
			System.out.println("header : "+s);
		});
		String authorization  = req.getHeader("authorization");
		System.out.println("auth :"+authorization);
		if(authorization != null && !authorization.equals("") && authorization.startsWith("Bearer ")) {
			System.out.println("start jwt 1");
			String token = authorization.substring(JwtUtils.AUTHENTICATION_SCHEME.length());
	        try {
	        	System.out.println("start jwt 2");
	            Jws<Claims> claims = JwtUtils.validateToken(token);
	            chain.doFilter(request, resp);
	        } catch (Exception e) {
	        	System.out.println("faillure jwt");
	        	RESTFilterRespModel.generate(resp, 498, "JWT Token is not valid --- error : \n"+e.getMessage());
	        	chain.doFilter(req, resp);
	        }
		}else {
			System.out.println("no jwt");
			RESTFilterRespModel.generate(resp, 404, "No JWT Found !");
//			chain.doFilter(req, resp);
		}
//		chain.doFilter(req, resp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
