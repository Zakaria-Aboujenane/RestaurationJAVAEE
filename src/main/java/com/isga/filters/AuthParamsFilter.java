package com.isga.filters;

import java.io.IOException;
import java.util.ArrayList;

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
import javax.servlet.http.HttpSession;

import com.isga.entities.Commande;
import com.isga.entities.LigneCommande;
import com.isga.entities.User;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.sessions.ISecurityBeanLocal;

@WebFilter(urlPatterns = {"/","/**/**","/*"})
public class AuthParamsFilter extends HttpFilter implements Filter {
    private static final long serialVersionUID = 1L;
    @EJB
    private ISecurityBeanLocal securityBean;
    @EJB
    private IEcomSessionBeanLocal metier;
	public AuthParamsFilter() {
        super();
    }
	public void destroy() {
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isAdmin = false;
		boolean isUser = false;
		boolean isLivreur = false;
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session  = req.getSession(false);
		boolean isAuthenticated = false;
		if(session != null && session.getAttribute("user") != null) {
			isAuthenticated = true;
			User user= (User) session.getAttribute("user");
			if(user != null) {
				Long idUser = user.getId_client();
				int nbrMenusEnPanier = 0;
				Commande com = metier.getUserCommandes(idUser)
						.stream()
						.filter(p->p.isCurrent())
						.findFirst()
						.orElse(null);
				if(com != null)
					nbrMenusEnPanier = metier.getLignesForCommande(com.getId_commande()).size();
				isAdmin = securityBean.hasRole(idUser,"ADMIN");
				isUser = securityBean.hasRole(idUser,"USER");
				isLivreur = securityBean.hasRole(idUser,"LIVREUR");
				req.setAttribute("isAdmin", isAdmin);
				req.setAttribute("isUser", isUser);
				req.setAttribute("isLivreur", isLivreur);
				req.setAttribute("nbrMenusPanier", nbrMenusEnPanier);
			}
			
		}
		req.setAttribute("isAdmin", isAdmin);
		req.setAttribute("isUser", isUser);
		req.setAttribute("isLivreur", isLivreur);
		req.setAttribute("isAuth", isAuthenticated);
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
