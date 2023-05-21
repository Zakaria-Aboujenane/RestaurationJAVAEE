package com.isga.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.net.ssl.HttpsURLConnection;
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

import com.google.gson.Gson;
import com.isga.model.ErrorAPIResponse;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet Filter implementation class AdminAPIFilters
 */
@WebFilter({
	"/api/restaurant/s/add-menu",
	"/api/restaurant/s/delete-menu",
	"/api/restaurant/s/update-menu",
	"/api/restaurant/s/get-all-commands",
	"/api/restaurant/s/toggle-valid-command"
	})
public class AdminAPIFilters extends HttpFilter implements Filter {
	
	@EJB
	private ISecurityBeanLocal securityBean;
       

    public AdminAPIFilters() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp  = (HttpServletResponse) response;
		String authorization  = req.getHeader("Authorization");
		if(JWTVerifyRole.verify(securityBean, authorization, "ADMIN")) {
			chain.doFilter(request, response);
		}else {
			
			RESTFilterRespModel.generate(resp, HttpsURLConnection.HTTP_UNAUTHORIZED, "No Privileges");
			chain.doFilter(req, resp);
		}

		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
