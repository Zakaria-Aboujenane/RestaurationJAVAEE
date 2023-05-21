package com.isga.controllers.menus;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isga.entities.Categorie;
import com.isga.entities.Menu;
import com.isga.sessions.IEcomSessionBeanLocal;
import com.isga.utils.FileImportJeeUtils;

@WebServlet(name = "AddMenuController", value = "/AddMenuController")
@MultipartConfig( fileSizeThreshold = 1024 * 1024,
				maxFileSize = 1024 * 1024 * 5,
				maxRequestSize = 2000 * 2000 * 5 * 5 )
public class AddMenuController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String IMAGES_FOLDER = "/images";
	@EJB
	private IEcomSessionBeanLocal metier;
    public String uploadPath;
    static Logger log = Logger.getLogger(AddMenuController.class.getName());
    boolean isEdit = false;
    public AddMenuController() throws ServletException {
		super.init();
    }
    @Override
    public void init() throws ServletException {
    	 uploadPath = FileImportJeeUtils.CreateAndGetUploadPath(getServletContext());
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("editId") != null) {
			isEdit = true;
		}
		request.setAttribute("isEdit", isEdit);
		isEdit=false;
		if(request.getAttribute("goTo") != null ) {
			if(request.getAttribute("goTo").equals("shop")) {
				request.getRequestDispatcher("./MenuController").forward(request, response);
			}
		}else {
			List<Categorie> cats = metier.listerCategories();
			request.setAttribute("cats", cats);
			
			request.getRequestDispatcher("WEB-INF/addMenu.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String titre = request.getParameter("titre");
	        String prix = request.getParameter("prix");
	        String qteMax = request.getParameter("qteMax");
	        String description = request.getParameter("description");
	        String idCat = request.getParameter("cat");
	        String action = request.getParameter("action");
	        Categorie categorie = metier.getCategorieById(Long.parseLong(idCat));
	        Menu m = new Menu();
	        m.setName(titre);
	        m.setPrix(Double.parseDouble(prix));
	        m.setQteMax(Long.parseLong(qteMax));
	        m.setDescription(description);
	        m.setCategorie(categorie);
	        if(action.equals("save")) {
	        	  m = metier.saveMenu(m);
	        }else if(action.equals("update")) {
	        	String idM = request.getParameter("id_menu").toString();
	        	Long id = Long.parseLong(idM);
	        	m.setId_menu(id);
	        }
	        String fileName = FileImportJeeUtils.getFileName(request.getPart("image"));
	        if(!fileName.equals("")) {
	        	 String img =  FileImportJeeUtils.storeSingleFile(request.getPart("image"),uploadPath,m.getId_menu().toString());
	        	 m.setImage(img);
	        }else {
	        	m.setImage(metier.getMenuById(m.getId_menu()).getImage());
	        }
	        metier.updateMenu(m.getId_menu(),m);
	        request.setAttribute("goTo", "shop");
	        doGet(request, response);
	}

}
