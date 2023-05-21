package com.isga.controllers.commandes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.Commande;
import com.isga.model.CommandModel;
import com.isga.sessions.IEcomSessionBeanLocal;

@WebServlet("/gestionCommande")
public class GestionCommandeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEcomSessionBeanLocal metier;
    public GestionCommandeController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Commande> cs = metier.listerCommandes()
				.stream()
				.filter(c->c.isCurrent() == false)
				.collect(Collectors.toList());
		List<CommandModel> cm = new ArrayList<>();
		cs.forEach(c->{
			c.setLigneCommandes(metier.getLignesForCommande(c.getId_commande()));
			CommandModel m = new CommandModel();
			m.setCom(c);
			m.setTotal(metier.getTotalOfCommande(c.getId_commande()));
			cm.add(m);
		});
		request.setAttribute("coms", cm);
		request.getRequestDispatcher("WEB-INF/g_commande.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
