<%@include file="layouts/menu.jsp" %>
<div class="container mt-5" >
<h1 class="mb-3">Mes Commandes</h1>
	<c:if test="${not empty msg}">
		 <div class="alert alert-danger" role="alert">
		  	${msg}
		</div>
	</c:if>
<ol class="list-group list-group-numbered">
<c:forEach items="${cms}" var="cm" >
  <li class="list-group-item d-flex justify-content-between align-items-start p-5">
    <div class="ms-2 me-auto">
      <div class="fw-bold">Commande Numero ${cm.com.id_commande} le ${cm.com.date}</div>
      <a class="btn" data-bs-toggle="collapse" href="#collapse${cm.com.id_commande}">
        voir plus
      </a>
         <div id="collapse${cm.com.id_commande}" class="collapse container" aria-expanded="false" data-bs-parent="#accordion">
         		<c:forEach items="${cm.com.ligneCommandes}" var="l">
         			<div class="card-body">
					    <h5 class="card-title">${l.menu.name} - ${l.qantite} pieces - ${l.menu.prix*l.qantite} DH</h5>
					    <p class="card-text"></p>
					  </div>
         		</c:forEach>
    	 </div>
    </div>
    <span class="badge bg-primary rounded-pill">${cm.total} DH</span>
    <span class="badge bg-primary rounded-pill">Livraison 25dh</span>
    <c:if test="${cm.com.valide}">
    	<span class="badge bg-success rounded-pill">Valide</span>
    </c:if>
        <c:if test="${!cm.com.valide}">
    	<span class="badge bg-danger rounded-pill">Non Valide</span>
    </c:if>
    
  </li>
  </c:forEach>
 </ol>
</div>


<%@include file="layouts/footer.jsp" %>