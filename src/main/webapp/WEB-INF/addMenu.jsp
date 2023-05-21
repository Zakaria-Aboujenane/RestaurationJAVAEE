<%@include file="layouts/menu.jsp" %>
<div class="container mt-5">

    <form method="post" action="./AddMenuController" enctype="multipart/form-data">
        <div class="row">
        	<input type="hidden" value="${menu.id_menu }" name="id_menu">
            <div class="col">
                <label for="Titre" class="form-label">Titre : </label>
                <input type="text" value="${menu.name }" class="form-control" id="Titre" placeholder="Entrez le titre" name="titre">
            </div>
            <div class="col">
                <label for="prix" class="form-label">Prix : </label>
                <input type="text" value="${menu.prix }" class="form-control" id="prix" placeholder="Entrez le prix" name="prix">
            </div>
             <div class="col">
                <label for="prix" class="form-label">quantite maximale : </label>
                <input type="text" value="${menu.qteMax }" class="form-control" id="qtemax" placeholder="qte maximale" name="qteMax">
            </div>
            <div class="col">
                <label class="form-label" for="image">Importez une photo</label>
                <input type="file" value="${menu.image }" class="form-control" id="image" name="image" />
            </div>
        </div>
        
         <div class="row">
         	<div class="col" >
         	<label for="comment">Selectionnez une categorie :</label>
		        <select name="cat" id="cat" class="form-select" aria-label="Selectionnez une categorie">
				 <c:forEach items="${cats}" var="cat" >
				 	<c:choose>
				 		<c:when test="${selectedCat.id_categorie == cat.id_categorie }">
				 			<option value="${cat.id_categorie}" selected="selected">${cat.name}</option>
				 		</c:when>
				 		<c:otherwise>
				 			<option value="${cat.id_categorie}">${cat.name}</option>
				 		</c:otherwise>
				 	</c:choose>
				 </c:forEach>
				</select>
			</div>
            <div class="col">
                <label for="comment">Description :</label>
                <textarea class="form-control" value="${menu.description }" rows="5" id="comment" name="description"></textarea>
            </div>
        </div>
        
        
       
        <div class="align-middle m-lg-5" >
        	<c:if test="${isEdit == false }">
        	 	<button type="submit" name="action" value="save" class="btn btn-primary align-items-center">Ajouter</button>
        	</c:if>
        	
           <c:if test="${isEdit == true }">
           		<button type="submit" name="action" value="update" class="btn btn-primary align-items-center">Modifier</button>
           </c:if>
             
        </div>

    </form>
</div>
<%@include file="layouts/footer.jsp" %>