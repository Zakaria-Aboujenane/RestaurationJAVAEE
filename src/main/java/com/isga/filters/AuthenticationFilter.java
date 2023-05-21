package com.isga.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = "/buy")
public class AuthenticationFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;
    private ServletContext context;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}


	public AuthenticationFilter() {
        super();
    }
	
	
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session  = req.getSession(false);
		if(session != null && session.getAttribute("user") != null) {
			chain.doFilter(request, response);
		}else {
			System.out.println("session est null");
			req.setAttribute("msg", "Veillez vous authentifier pour faire ces operations");
			request.getRequestDispatcher("/login").forward(req, resp);
		}
		
//		request.getServletContext().getServlet()
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
