package com.isga.controllers.commandes;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.Commande;
import com.isga.entities.LigneCommande;
import com.isga.entities.User;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.sessions.ISecurityBeanLocal;

@WebServlet(name = "cart", value = "/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEcomSessionBeanLocal metier;
	@EJB
	private ISecurityBeanLocal secMetier;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User u = (User) request.getSession().getAttribute("user");
    	Long idCom=null;
    	if(request.getAttribute("idCom") != null) {
    		 idCom = Long.parseLong(request.getAttribute("idCom").toString());
    	}else {
    		List<Commande> commandes = metier.getUserCommandes(u.getId_client());
        	Commande com = commandes.stream().filter(p->p.isCurrent()).findFirst().orElse(null);
        	if(com !=null)
        		idCom = com.getId_commande();
    	}
    	if(idCom != null) {
			List<LigneCommande> cartProds = metier.getLignesForCommande(idCom);
	    	request.setAttribute("com", cartProds);
	    	request.setAttribute("total", metier.getTotalOfCommande(idCom));
	    	request.setAttribute("idc", idCom);
	    	request.getRequestDispatcher("WEB-INF/cart_view.jsp").forward(request, response);
    	}else {
    		request.setAttribute("msg", "pas de menus , veuillez remplir votre panier");
    		request.getRequestDispatcher("WEB-INF/cart_view.jsp").forward(request, response);
    	}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}