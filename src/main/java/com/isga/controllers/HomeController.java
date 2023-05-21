package com.isga.controllers;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.security.auth.spi.Users;

import com.isga.entities.Categorie;
import com.isga.entities.Menu;
import com.isga.entities.Role;
import com.isga.entities.User;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.utils.PasswordEncoder;
@WebServlet(name = "home", value = "/home")
public class HomeController extends HttpServlet {
	@EJB
	private IEcomSessionBeanLocal metier;
	@EJB
	private ISecurityBeanLocal securityMetier;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		MenusGenerator();
    	UsersGeneration();
	}
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    public void MenusGenerator() {
    	Categorie cat= new Categorie(null,"Dejeuner");
		Categorie cat2= new Categorie(null,"petit dejeuner");
		Categorie cat3= new Categorie(null,"casse croute");
		metier.saveCategory(cat3);
		metier.saveCategory(cat2);
		metier.saveCategory(cat);
		Categorie[] cats = new Categorie[] {cat,cat2,cat3};

    	String[] ss = new String[] {
    			"Pizza","kuskus","tacos","bastila","panini","hergma","poulet","tanjya"
    	};
    	String desc = "This seared foxtail millet salad is a little intoxicating with a velvety texture. It is infused with green peas with tulsi / holy basil and has rosemary. It smells like chlorine with just the right amount of gourd. It is peculiarly sweet. You can really feel how low calorie and how 0g of added sugar it is.";
    	
    	Random r = new Random();
    	for(int i=0;i<53;i++) {
    		int result = r.nextInt(7-0) + 0;
    		int prix = r.nextInt(40-20)+20;
    		Long qte = (long) (r.nextInt(30-10)+10);
    		int randomCat = ThreadLocalRandom.current().nextInt(0,2);
    		Menu m = new Menu(null,ss[result],desc,prix,qte,"pic.jpg");
    		m.setCategorie(cats[randomCat]);
    		metier.saveMenu(m);
    	}
    	
    }
    
    public void UsersGeneration() {
    		User u = new User(null,"Zakaria","zak@z.ma","1234");
    		User u1 = new User(null,"Meriem","mer@z.ma","1234");
    		User u2 = new User(null,"Ahmed","ahm@z.ma","1234");
    		User u3 = new User(null,"Abdelali","abdel@z.ma","1234");
    		securityMetier.saveUser(u);
    		securityMetier.saveUser(u1);
    		securityMetier.saveUser(u2);
    		securityMetier.saveUser(u3);
    		
    		securityMetier.saveRole("ADMIN");
    		securityMetier.saveRole("USER");
    		securityMetier.saveRole("LIVREUR");
    		
    		securityMetier.addRoleToUser(1L, "ADMIN");
    		securityMetier.addRoleToUser(1L, "USER");
    		
    		securityMetier.addRoleToUser(2L, "USER");
    		securityMetier.addRoleToUser(2L, "LIVREUR");
    		
    		securityMetier.addRoleToUser(3L, "USER");
    		
    		securityMetier.addRoleToUser(4L, "USER");
    }
}
