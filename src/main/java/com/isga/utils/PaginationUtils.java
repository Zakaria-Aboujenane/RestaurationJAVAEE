package com.isga.utils;

import java.util.List;


public abstract class PaginationUtils<T> {
	protected int page=1;
	protected int perPage=5;
	protected int currPage=1;
	protected int totalPages;
	protected List<T> listOfObjects;
    
    public PaginationUtils(List<T> listOfObjects) {
		super();
		this.listOfObjects = listOfObjects;
		getTotalPages();
	}

	public int getTotalPages() {
    	this.totalPages = listOfObjects.size()/perPage;
    	if(listOfObjects.size()%perPage != 0) {
    		this.totalPages ++;
    	}
    	return totalPages;
    }
	public List<T> listPage(int pageSelected){
			this.currPage = pageSelected;
			int fIndex = pageSelected*perPage;
			if(fIndex < this.listOfObjects.size()) {
				return this.listOfObjects.subList(fIndex-perPage,fIndex);
			}
			if(fIndex-perPage < this.listOfObjects.size()){
				return this.listOfObjects.subList(fIndex-perPage,listOfObjects.size());
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

	public List<T> getList() {
		return listOfObjects;
	}

	public void setList(List<T> listOfObjects) {
		this.listOfObjects = listOfObjects;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
