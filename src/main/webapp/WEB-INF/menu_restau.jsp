
<%@include file="layouts/menu.jsp" %>

<div class="container mt-5" >

    <div class="modal fade bg-white" id="templatemo_search" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="w-100 pt-1 mb-5 text-right">
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="" method="get" class="modal-content modal-body border-0 p-0">
                <div class="input-group mb-2">
                    <input type="text" class="form-control" id="inputModalSearch" name="q" placeholder="Search ...">
                    <button type="submit" class="input-group-text bg-success text-light">
                        <i class="fa fa-fw fa-search text-white"></i>
                    </button>
                </div>
            </form>
        </div>
    </div>
     <div class="container py-5">
        <div class="row">

            <div class="col-lg-3">
                <h1 class="h2 pb-4">Categories</h1>
                <ul class="list-unstyled templatemo-accordion">
                  <c:forEach items="${model.listeCat}" var="cat">
                       <li class="pb-3">
	                        <a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="#">
	                            ${cat.name}
	                            <i class="fa fa-fw fa-chevron-circle-right mt-1"></i>
	                        </a>
                    	</li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col-lg-9">
                <div class="row">
                <c:forEach items="${model.listeMenu}" var="menu">
                	<div class="position-relative col-md-4">
                	
                        <div class="card mb-4 product-wap rounded-0">
                        
							  
			
                            <div class="z-2 card rounded-0">
                                <img class="card-img rounded-0 img-fluid" src="./images/${menu.image}">
                                <div class="card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center">
                                    <ul class="list-unstyled">
                                        <li><a class="btn btn-success text-white mt-2" href="./show-single?id=${menu.id_menu}"><i class="far fa-eye"></i></a></li>
                                        <c:if test="${isAdmin}">
                                        	 <li><a class="btn btn-info text-white mt-2" href="./MenuController?editId=${menu.id_menu}"><i class="fas fa-edit"></i></a></li>
                                        	<li><a class="btn btn-danger text-white mt-2" href="./MenuController?deleteId=${menu.id_menu}"><i class="fa fa-trash" aria-hidden="true"></i></a></li>
                                        </c:if>
                                       
                                    
                                    </ul>
                                </div>
                            </div>
                            
                            <div class="card-body">
                                <a href="shop-single.html" class="h3 text-decoration-none">${menu.name}</a>
                               
                                <p class="text-center mb-0">${menu.prix} DH </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
                    
                <div div="row">
                 <ul class="pagination pagination-lg justify-content-end">
	                <c:forEach begin="${pagination.page}" end="${pagination.totalPages}" varStatus="p">
	                       
	                            <a class="page-link active rounded-0 mr-3 shadow-sm border-top-0 border-left-0" href="./MenuController?page=${p.index}" tabindex="-1"> ${p.index} </a>
	                        </li>
	                </c:forEach>
	                </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- End Content -->
    
	
</div>

<%@include file="layouts/footer.jsp" %>
