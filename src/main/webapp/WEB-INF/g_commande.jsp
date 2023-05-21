<%@include file="layouts/menu.jsp" %>
<div class="container-lg mt-5">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Liste des  <b>Commandes</b></h2></div>
                    <div class="col-sm-4">
                        <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> Add New</button>
                    </div>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                    	<th>ID</th>
                    	<th>Titre</th>
                        <th>date</th>
                        <th>prix</th>
                        <th>Etat</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${coms}" var="c">
                    <tr>
                    	<td>${c.com.id_commande}</td>
                        <td>${c.com.titre}</td>
                        <td>${c.com.date}</td>
                        <td>${c.total+25.00} DH</td>
                        <td id="etat${c.com.id_commande}"> 
                        	<c:if test="${c.com.valide}">
                        		<span class="badge bg-success rounded-pill">Valide</span>
                        	</c:if>
                        	<c:if test="${!c.com.valide}">
                        		<span class="badge bg-danger rounded-pill">Non Valide</span>
                        	</c:if>
                        </td>
                        <td id="btn${c.com.id_commande}">
                        	<c:if test="${!c.com.valide}">
	                        	<button type="button" onclick="togValid(${c.com.id_commande})" class="btn btn-success">
									  Valider
								</button>
							</c:if>
							<c:if test="${c.com.valide}">
	                        	<button type="button" onclick="togValid(${c.com.id_commande})" class="btn btn-danger">
									  invalider
								</button>
							</c:if>
						</td>
                        <td>
                            <a class="text-dark mr-3" title="Edit" href="#"  data-bs-toggle="collapse" href="#collapse${cm.com.id_commande}" >
                            	<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal${cm.com.id_commande}">
								  <i class="fa fa-info-circle mr-3" aria-hidden="true"></i>Details
								</button>
								
								<!-- Modal -->
								<div class="modal fade" id="modal${cm.com.id_commande}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Details de la commande</h5>
								        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								      </div>
								      <div class="modal-body">
								        <c:forEach items="${c.com.ligneCommandes}" var="l">
						         			<div class="card-body">
											    <h5 class="card-title">${l.menu.name} - ${l.qantite} pieces - ${l.menu.prix*l.qantite} DH</h5>
											    <p class="card-text"></p>
											  </div>
						         		</c:forEach>
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Fermer</button>
								      </div>
								    </div>
								  </div>
								</div>
                            </a>
                        </td>
                       
                    </tr>
                   
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>   
<div id="demo">OKK</div>
 <script type="text/javascript">
 function togValid(idc) {
	 alert("hellooha");
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	    	 document.getElementById("demo").innerHTML = this.responseText;
	     	if(this.responseText === "true"){
	     		 document.getElementById("etat"+idc).innerHTML = '<span class="badge bg-success rounded-pill">Valide</span>';
	     		 document.getElementById("btn"+idc).innerHTML = '<button type="button" onclick="togValid('+idc+')" class="btn btn-danger">Invalider</button>'
	     	}else if(this.responseText === "false"){
	     		document.getElementById("etat"+idc).innerHTML = '<span class="badge bg-danger rounded-pill">Non Valide</span>';
	     		document.getElementById("btn"+idc).innerHTML = '<button type="button" onclick="togValid('+idc+')" class="btn btn-success">Valider</button>'
	     	}
	    }
	  };
	  xhttp.open("GET", "./ajax?action=toggValid&idc="+idc, true);
	  xhttp.send();
	}
</script>

<%@include file="layouts/footer.jsp" %> 