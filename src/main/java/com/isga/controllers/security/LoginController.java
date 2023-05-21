package com.isga.controllers.security;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.wildfly.security.password.Password;

import com.isga.entities.User;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.utils.PasswordEncoder;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private ISecurityBeanLocal securityBean;
    public LoginController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("user") != null) {
			String p= request.getContextPath() +"/home";
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			if(request.getAttribute("msg") != null) {
				String msg = request.getAttribute("msg").toString();
				request.setAttribute(msg, msg);
			}
			request.getRequestDispatcher("WEB-INF/loginForm.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email").toString();
		String pswd = request.getParameter("pwd").toString();
		User user = securityBean.authUser(email,pswd);
		if(user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
		}else {
			request.setAttribute("msg", "Email ou mdp incorrect");
			
		}
		doGet(request, response);
		
	}

}
