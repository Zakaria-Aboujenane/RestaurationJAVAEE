package com.isga.controllers.commandes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.Commande;
import com.isga.entities.Menu;
import com.isga.entities.User;
import com.isga.model.CommandModel;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.sessions.ISecurityBeanLocal;
@WebServlet("/commande")
public class UserCommadesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEcomSessionBeanLocal metier;
	@EJB
	private ISecurityBeanLocal securityMetier;

    public UserCommadesController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute("user");
		Long idCom = 0L;
		if(request.getParameter("idc") != null) {
			idCom = Long.parseLong(request.getParameter("idc").toString());
			metier.terminerCommande(idCom);
		}
		List<Commande> cs = metier.getUserCommandes(u.getId_client())
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
		if(cm.size()==0) {
			request.setAttribute("msg", "Pas de commandes pour l'instant");
		}
		request.setAttribute("cms",cm);
		request.getRequestDispatcher("WEB-INF/user_commandes.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
