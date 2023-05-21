package com.isga.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.Email;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.isga.entities.Categorie;
import com.isga.entities.Commande;
import com.isga.entities.LigneCommande;
import com.isga.entities.Menu;
import com.isga.entities.User;
import com.isga.model.EmailPassowrdModel;
import com.isga.model.ErrorAPIResponse;
import com.isga.model.PaginationModel;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.utils.JwtUtils;
@Stateless
@Path("restaurant")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class EcomRestService{
	private PaginationModel paginationModel;
	@EJB
	private  IEcomSessionBeanLocal metier;
	

	@EJB
	private ISecurityBeanLocal security;
// security
	
	@POST
	@Path("/login")
	public Response login(EmailPassowrdModel emailPassowrdModel){
		Map<String, Object> mMap = new HashMap<>();
		User u = security.authUser(emailPassowrdModel.getEmail(), emailPassowrdModel.getPassword());
		if(u!=null) {
			String jwtToken = JwtUtils.generateToken(u.getName());
			mMap.put("status",200);
			mMap.put("access-token",jwtToken);
			mMap.put("username", u.getName());
			return Response.ok(mMap).build();
		}else {
			mMap.put("status",401);
			mMap.put("error-message", "Email or password invalid");
			return Response.status(404, "Email or Password invalid").build();
		}
	}
// commandes
	@GET
	@Path("/s/get-all-commands")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commande> listerCommandes() {
		List<Commande> cs = metier.listerCommandes();
		cs.forEach(
				c->{
					c.setLigneCommandes(null);
				}
				);
		return cs;
	}

	public Commande saveCommande(Commande commande) {
		return metier.saveCommande(commande);
	}

	public Commande getCommandebyId(Long idCommande) {
		return metier.getCommandebyId(idCommande);
	}

	public double getTotalOfCommande(Long idCommande) {
		return metier.getTotalOfCommande(idCommande);
	}

	public void changerEtatCommande(MenuCommandeModel model) {
		metier.changerEtatCommande(new Long(model.getIdCommande()));
	}
	@POST
	@Path("/s/commander")
	public void terminerCommande(MenuCommandeModel model) {
		metier.terminerCommande(new Long(model.getIdCommande()));
	}

	public List<Commande> getUserCommandes(Long userID) {
		return metier.getUserCommandes(userID);
	}
	@GET
	@Path("/s/get-lignes-for-commande")
	public List<LigneCommande> getLignesForCommande(@QueryParam(value = "idc") Long idCommande) {
		
		List<LigneCommande> ls = metier.getLignesForCommande(idCommande);
		ls.forEach(l->{
			l.getCommande().setLigneCommandes(null);
			l.getCommande().getUser().setCommandes(null);
			l.getMenu().setLigneCommandes(null);
			l.getMenu().getCategorie().setMenus(null);
		});
		return ls;
	}
//menus 
	@GET
	@Path("menus")
	public List<Menu> listerMenus() {
		return metier.listerMenus();
	}
	@GET
	@Path("menus_pagination")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Menu> listerMenusPagination(
			@QueryParam(value = "page") int page,
			@QueryParam(value = "perPage") int perPage){
		if(metier.listerMenus().size()>0) {
			List<Menu> ms = metier.listerMenus();
			
			ms.forEach(m->{
				m.setLigneCommandes(null);
				m.getCategorie().setMenus(null);
			});
			paginationModel = new PaginationModel(ms);
			paginationModel.setPerPage(perPage);
			return paginationModel.listPage(page);
		}else {
			return new ArrayList<Menu>();
		}
		
	}
	@GET
	@Path("total_pages")
	public int totalPages(@QueryParam(value = "perPage") int perPage){
		paginationModel = new PaginationModel(metier.listerMenus());
		paginationModel.setPerPage(perPage);
		return paginationModel.getTotalPages();
	}
	@POST
	@Path("/menus")
	public Menu saveMenu(Menu menu) {
		return metier.saveMenu(menu);
	}
	@GET
	@Path("/menu")
	@Produces(MediaType.APPLICATION_JSON)
	public Menu getMenuById(@QueryParam(value = "id") Long idMenu) {
		Menu m = metier.getMenuById(idMenu);
		m.getCategorie().setMenus(null);
		return m;
	}

	public Menu updateMenu(Long idMenu, Menu m) {
		return metier.updateMenu(idMenu, m);
	}

	public void deleteMenu(Long idMenu) {
		metier.deleteMenu(idMenu);
	}
	@POST
	@Path("/s/add-menu-to-commande")
	public void addMenuToCommande(MenuCommandeModel mcm){
		System.out.println("adding menu "+mcm.getIdMenu()+" to commande "+mcm.getIdCommande() );
		metier.addMenuToCommande(mcm.getIdMenu(), mcm.getIdCommande(), mcm.getQte());
	}
	@POST
	@Path("/s/delete-menu-from-commande")
	public void deleteMenuFromCommande(MenuCommandeModel model) {
		metier.deleteMenuFromCommande(model.getIdMenu(), model.getIdCommande());
	}
	@GET
	@Path("search_menus")
	public List<Menu> searchMenus(@QueryParam(value = "k") String k){
		List<Menu> ms = metier.searchMenus(k);
		ms.forEach(m->{
			m.getCategorie().setMenus(null);
		});
		return ms;
	}
// categories
	@GET
	@Path("categories")
	public List<Categorie> listerCategories() {
		List<Categorie> cs = metier.listerCategories();
		cs.forEach(c->{
			c.setMenus(null);
		});
		return cs;
	}

	public Categorie saveCategory(Categorie cat) {
		return metier.saveCategory(cat);
	}

	public Categorie getCategorieById(Long idCategorie) {
		return metier.getCategorieById(idCategorie);
	}
	@GET
	@Path("menus_by_cat")
	public List<Menu> getMenusByCategorie(@QueryParam(value = "cat") Long idCategorie) {
		List<Menu> ms = new ArrayList<>(metier.getMenusByCategorie(idCategorie));
		ms.forEach(m->{
			m.getCategorie().setMenus(null);
		});
		return ms;
	}
// livraison
	public double getLivraisonDefault() {
		return metier.getLivraisonDefault();
	}
// user et commande
	@GET
	@Path("/s/get-curr-user-commande")
	@Produces(MediaType.APPLICATION_JSON)
	public Commande getUserCurrentCommande(@QueryParam(value="u") String username) {
		System.out.println("get user commmande executed");
		Commande c = metier.getUserCurrentCommande(username);
		c.setLigneCommandes(null);
		c.getUser().setCommandes(null);
		return c;
	}
}


