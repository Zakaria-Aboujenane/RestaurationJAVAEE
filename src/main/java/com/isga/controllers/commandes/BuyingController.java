package com.isga.controllers.commandes;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.isga.entities.Commande;
import com.isga.entities.Menu;
import com.isga.entities.User;
import com.isga.services.EcomRestService;

import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.utils.PaginationModel;

@WebServlet(name = "buying", value = "/buy")
public class BuyingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEcomSessionBeanLocal metier;
	@EJB
	private ISecurityBeanLocal secMetier;
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String action = request.getParameter("submit");
    	Long qte = Long.parseLong(request.getParameter("product-quanity").toString());
    	Long id = Long.parseLong(request.getParameter("product-id").toString());
    	User u = (User) request.getSession(false).getAttribute("user");
    	// transform to service layer code for reuse (getUserCurrentCommande())
       	List<Commande> commandes = metier.getUserCommandes(u.getId_client());
    	Commande comm = commandes.stream().filter(p->p.isCurrent()).findFirst().orElse(null);
		if(comm == null) {
			comm = new Commande();
			comm.setTitre("Commande");
	    	comm.setFraisLivraison(25.00);
			comm.setUser(u);
			comm.setDate(new Date());
			comm.setValide(false);
			comm.setCurrent(true);
			comm = metier.saveCommande(comm);
		}
    	metier.addMenuToCommande(id, comm.getId_commande(), qte);
    	
    	if(action.equals("buy")) {
    		request.setAttribute("idCom", comm.getId_commande());
    		
    	}else if(action.equals("addtocard")) {
    		
    	}else if(action.equals("delete")) {
    		metier.deleteMenuFromCommande(id, comm.getId_commande());
    	}
    	request.getRequestDispatcher("/cart").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}