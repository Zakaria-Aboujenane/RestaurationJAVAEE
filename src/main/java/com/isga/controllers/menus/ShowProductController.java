package com.isga.controllers.menus;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.Menu;
import com.isga.model.Model;
import com.isga.sessions.IEcomSessionBeanLocal;


@WebServlet("/show-single")
public class ShowProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEcomSessionBeanLocal metier;
    public ShowProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("id").toString();
		Long idMenu =  Long.parseLong(s);
		Menu m = metier.getMenuById(idMenu);
		request.setAttribute("model", m);
		request.getRequestDispatcher("WEB-INF/single_product.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
