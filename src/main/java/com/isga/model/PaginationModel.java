package com.isga.model;

import java.util.List;

import com.isga.entities.Menu;

public class PaginationModel {
    private int page=1;
    private int perPage=5;
    private int currPage=1;
    private int totalPages;
    private List<Menu> menus;
    
    public PaginationModel(List<Menu> menus) {
		super();
		this.menus = menus;
		getTotalPages();
	}

	public int getTotalPages() {
    	this.totalPages = menus.size()/perPage;
    	if(menus.size()%perPage != 0) {
    		this.totalPages ++;
    	}
    	return totalPages;
    }
	public List<Menu> listPage(int pageSelected){
			this.currPage = pageSelected;
			int fIndex = pageSelected*perPage;
			if(fIndex < this.menus.size()) {
				return this.menus.subList(fIndex-perPage,fIndex);
			}
			if(fIndex-perPage < this.menus.size()){
				return this.menus.subList(fIndex-perPage,menus.size());
			}else {
				return listPage(getTotalPages());
			}		
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
    
}
