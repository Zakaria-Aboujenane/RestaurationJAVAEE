<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Zay Shop eCommerce HTML CSS Template</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="assets/img/apple-icon.png">
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.ico">

    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/templatemo.css">
    <link rel="stylesheet" href="assets/css/custom.css">

    <!-- Load fonts style after rendering the layout styles -->
    <link rel="stylesheet" href="assets/css/fontawesome.min.css">
    
    
<!--
    
TemplateMo 559 Zay Shop

https://templatemo.com/tm-559-zay-shop

-->
</head>

<body>
    <!-- Start Top Nav -->
    <nav class="navbar navbar-expand-lg bg-dark navbar-light d-none d-lg-block" id="templatemo_nav_top">
        <div class="container text-light">
            <div class="w-100 d-flex justify-content-between">
                <div>
                    <i class="fa fa-envelope mx-2"></i>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="mailto:info@company.com">info@company.com</a>
                    <i class="fa fa-phone mx-2"></i>
                    <a class="navbar-sm-brand text-light text-decoration-none" href="tel:010-020-0340">010-020-0340</a>
                </div>
                <div>
                    <a class="text-light" href="https://fb.com/templatemo" target="_blank" rel="sponsored"><i class="fab fa-facebook-f fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://www.instagram.com/" target="_blank"><i class="fab fa-instagram fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://twitter.com/" target="_blank"><i class="fab fa-twitter fa-sm fa-fw me-2"></i></a>
                    <a class="text-light" href="https://www.linkedin.com/" target="_blank"><i class="fab fa-linkedin fa-sm fa-fw"></i></a>
                </div>
            </div>
        </div>
    </nav>
    <!-- Close Top Nav -->


    <!-- Header -->
    <nav class="navbar navbar-expand-lg navbar-light shadow">
        <div class="container d-flex justify-content-between align-items-center">

            <a class="navbar-brand text-success logo h1 align-self-center" href="./home">
                Zaki-E-Com
            </a>

            <button class="navbar-toggler border-0" type="button" data-bs-toggle="collapse" data-bs-target="#templatemo_main_nav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between" id="templatemo_main_nav">
                <div class="flex-fill">
                    <ul class="nav navbar-nav d-flex justify-content-between mx-lg-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="./home">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="./MenuController">Shop</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact.html">Contact</a>
                        </li>
                    </ul>
                </div>
                
                <div class="navbar align-self-center d-flex">
                    <div class="d-lg-none flex-sm-fill mt-3 mb-4 col-7 col-sm-auto pr-3">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMobileSearch" placeholder="Search ...">
                            <div class="input-group-text">
                                <i class="fa fa-fw fa-search"></i>
                            </div>
                        </div>
                    </div>
                         <a class="nav-icon d-none d-lg-inline" href="#" data-bs-toggle="modal" data-bs-target="#templatemo_search">
	                        <i class="fa fa-fw fa-search text-dark mr-2"></i>
	                    </a>
                    <c:if test="${isAuth}">
	              		
						
	                    <a class="nav-icon position-relative text-decoration-none" href="./cart">
	                        <i class="fa fa-fw fa-cart-arrow-down text-dark mr-1"></i>
	                        <span id="panierLabelID" class="position-absolute top-0 left-100 translate-middle badge rounded-pill bg-light text-dark">${nbrMenusPanier}</span>
	                    </a>
	                   
	                     <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
						   	<i class="fa fa-fw fa-user text-dark mr-3"></i>
						  </a>
	                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
	                    <li>  <div class="text-success" role="alert">
								  <i class="fa fa-fw fa-user text-dark mr-3"></i>	${user.name }
						</div> </li>
	                    <li><a class="dropdown-item" href="#">Mon profile</a></li>
	                    	<c:if test="${isUser}">
	                    		<li><a class="dropdown-item" href="./commande">Mes Commandes</a></li>
	                    	</c:if>
						    <c:if test="${isAdmin}">
	                    		<li><a class="dropdown-item" href="./gestionCommande">Gestion des commandes</a></li>
	                    		<li><a class="dropdown-item" href="./AddMenuController">Ajouter un menu</a></li>
	                    	</c:if>
	                    	<c:if test="${isLivreur}">
	                    		<li><a class="dropdown-item" href="#">Livraisons</a></li>
	                    	</c:if>
	                    	<li><a class="dropdown-item" href="./logout">
			                    	<button type="button" class="btn btn-dark">
			                    		se deconnecter
			                    	</button>
	                    	</a></li>
						 </ul>
                    </c:if>
                    <c:if test="${!isAuth}">
                		<a class="nav-icon d-none d-lg-inline" href="./login" >
	                        <button type="button" class="btn btn-dark">Login</button>
	                    </a>
	                    <a class="nav-icon d-none d-lg-inline" href="./register">
	                        <button type="button" class="btn btn-dark">Register</button>
	                    </a>
                	</c:if>
                </div>
                
                
            </div>

        </div>
    </nav>
    <!-- Close Header -->
