<%@include file="layouts/menu.jsp" %>

<section class="h-100 h-custom" style="background-color: white;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12">
        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
          <div class="card-body p-0">
            <div class="row g-0">
              <div class="col-lg-8">
                <div class="p-5">
                  <div class="d-flex justify-content-between align-items-center mb-5">
                    <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
                    <h6 class="mb-0 text-muted">${com.size()} items</h6>
                  </div>
                  <hr class="my-4">
                  	<div id="errorPlace">
						<c:if test="${not empty msg}">
                           		 <div class="alert alert-danger" role="alert">
								  	${msg}
								</div>
                           </c:if>
                     </div>
                  <c:forEach  items="${com}" var="ligne">
		                  <div id="menu${ligne.menu.id_menu }" class="row mb-4 d-flex justify-content-between align-items-center">
		                    <div class="col-md-2 col-lg-2 col-xl-2">
		                      <img
		                        src="images/${ligne.menu.image}"
		                        class="img-fluid rounded-3" alt="Cotton T-shirt">
		                    </div>
		                    <div class="col-md-3 col-lg-3 col-xl-3">
		                      <h6 class="text-muted">${ligne.menu.categorie.name}</h6>
		                      <h6 class="text-black mb-0">${ligne.menu.name}</h6>
		                    </div>
		                    <div class="col-md-3 col-lg-3 col-xl-3 d-flex">
		                      <ul class="list-inline pb-3 mb-0" >
		                       			<li class="list-inline-item"><span class="btn btn-success" onclick="incDec(${ligne.menu.id_menu},${ligne.commande.id_commande},-1)" id="btn-minus">-</span></li>
		                                <li class="list-inline-item"><span class="badge bg-secondary" id="var-value${ligne.menu.id_menu }">${ligne.qantite}</span></li>
		                                <li class="list-inline-item"><span class="btn btn-success" onclick="incDec(${ligne.menu.id_menu},${ligne.commande.id_commande},1)" id="btn-plus">+</span></li>
		                      </ul>
		                    </div>
		                    <div class="col-md-3 col-lg-3 col-xl-2 offset-lg-1">
		                      <h6 class="mb-0 m">${ligne.menu.prix} DH</h6>
		                    </div>
		                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
		                      <a href="#!" onclick="deleteCM(${ligne.menu.id_menu},${ligne.commande.id_commande })" class="text-muted"><i class="fas fa-times"></i></a>
		                    </div>
		                  </div>
                  </c:forEach>

                  <hr class="my-4">

                  

                  <div class="pt-5">
                    <h6 class="mb-0"><a href="./MenuController" class="text-body"><i
                          class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                  </div>
                </div>
              </div>
              
              <div class="col-lg-4 bg-grey">
                <div class="p-5">
                  <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                  <hr class="my-4">
				
                  <div class="d-flex justify-content-between mb-4">
                    <h5 class="text-uppercase">Prix des menus</h5>
                    <h5>${total} DH</h5>
	              </div>
	              <div class="d-flex justify-content-between mb-4">
                    <h5 class="text-uppercase">Livraison</h5>
                    <h5>25.0 DH</h5>
	              </div>
	              <div class="d-flex justify-content-between mb-4">
                    <h5 class="text-uppercase">Total</h5>
                    <h5>${total + 25.00} DH</h5>
	              </div>
					<form action="./commande" method="get">
					  <input type="hidden" id="custId" name="idc" value="${idc}">
	                  <button type="submit" class="btn btn-dark btnblock btn-lg"
	                    data-mdb-ripple-color="dark">Commander</button>
	                </form>
	               </div>
	             </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<script type="text/javascript">
 function incDec(idM,idC,qte) {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	document.getElementById("var-value"+idM).innerHTML= this.responseText;
	    }
	  };
	  xhttp.open("GET", "./ajax?action=incDec&idC="+idC+"&idM="+idM+"&qte="+qte, true);
	  xhttp.send();
	}
 function deleteCM(idM,idC) {
	 var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	document.getElementById("menu"+idM).outerHTML = "";
	    	if(this.responseText != ""){
	    		document.getElementById("errorPlace").innerHTML='<div class="alert alert-danger" role="alert">'+this.responseText+'</div>';
	    	}
	    
	    }
	  };
	  xhttp.open("GET", "./ajax?action=delete&idC="+idC+"&idM="+idM, true);
	  xhttp.send();
 }
</script>
<%@include file="layouts/footer.jsp" %>