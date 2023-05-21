package com.isga.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.LigneCommande;
import com.isga.sessions.IEcomSessionBeanLocal;

@WebServlet("/ajax")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IEcomSessionBeanLocal metier;
    public AjaxController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = (String) request.getParameter("action");	
		if(action != null) {
				if(action.equals("toggValid")) {
					Long idC = Long.parseLong(request.getParameter("idc").toString());
					metier.changerEtatCommande(idC);
					response.getWriter().write(metier.getCommandebyId(idC).isValide()+"");
				}else if(action.equals("incDec")) {
					Long idC = Long.parseLong(request.getParameter("idC").toString());
					Long idM = Long.parseLong(request.getParameter("idM").toString());
					Long qte = Long.parseLong(request.getParameter("qte").toString());
					metier.addMenuToCommande(idM, idC, qte);
					LigneCommande ligne = metier.getLignesForCommande(idC)
							.stream()
							.filter(l->l.getMenu().getId_menu() == idM)
							.findFirst()
							.orElseGet(null);
					if(ligne != null) {
						response.getWriter().write(ligne.getQantite().toString());
					}
					System.out.println("qte modifiee");
				}else if(action.equals("delete")) {
					Long idC = Long.parseLong(request.getParameter("idC").toString());
					Long idM = Long.parseLong(request.getParameter("idM").toString());
					metier.deleteMenuFromCommande(idM, idC);
					System.out.println("produit supprime de la commande");
					if(metier.getLignesForCommande(idC).size() ==0) {
						response.getWriter().write("pas de commandes");
					}
				}
			}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
