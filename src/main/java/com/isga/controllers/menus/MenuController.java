package com.isga.controllers.menus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.Categorie;
import com.isga.entities.Menu;
import com.isga.model.Model;
import com.isga.model.PaginationModel;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.utils.FileImportJeeUtils;

import javax.servlet.annotation.MultipartConfig;
@WebServlet(name = "MenuController", value = "/MenuController")
public class MenuController extends HttpServlet {
	@EJB
	private IEcomSessionBeanLocal metierEcom;
    List<Menu> menus;
    List<Categorie> cats;
    String uploadPath;
    PaginationModel paginationModel;

    @Override
    public void init() throws ServletException {
         super.init();
         menus = metierEcom.listerMenus();
         cats = metierEcom.listerCategories();
         paginationModel = new PaginationModel(menus);
         uploadPath = FileImportJeeUtils.CreateAndGetUploadPath(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String editId = request.getParameter("editId");
    	String deleteId = request.getParameter("deleteId");
    	if(deleteId != null) {
       		Long id = Long.parseLong(deleteId);
       		metierEcom.deleteMenu(id);
       	}
    	if(editId!=null) {
	       		 Long id = Long.parseLong(editId);
	       		 Menu m = metierEcom.getMenuById(id);
	       		 request.setAttribute("menu", m);
	       		 request.setAttribute("selectedCat", m.getCategorie());
	       		 request.getRequestDispatcher("/AddMenuController?editId=1").forward(request, response);
    	}else {

        	Model model = new Model(new ArrayList<>());
        	paginationModel = new PaginationModel(metierEcom.listerMenus());
        	model.setListeCat(metierEcom.listerCategories());
        	if(request.getParameter("page")!=null) {
        		int pageSelected =   Integer.parseInt(request.getParameter("page").toString());
        		model.setListeMenu(paginationModel.listPage(pageSelected));
        	}else {
    	        model.setListeMenu(paginationModel.listPage(1));
        	}
        	request.setAttribute("model", model);
            request.setAttribute("pagination", paginationModel);
            request.getRequestDispatcher("WEB-INF/menu_restau.jsp").forward(request, response);
    	}
    	
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
