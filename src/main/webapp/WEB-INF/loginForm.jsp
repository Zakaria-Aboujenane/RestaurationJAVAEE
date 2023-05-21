<%@include file="layouts/menu.jsp" %>

<div class="container-fluid vh-100 mt-5">
            <div class="">
                <div class="rounded d-flex justify-content-center">
                    <div class="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
                        <div class="text-center">
                            <h3 class="text-success">Sign In</h3>
                           <c:if test="${not empty msg}">
                           		 <div class="alert alert-danger" role="alert">
								  	${msg}
								</div>
                           </c:if>
                        </div>
                        <form action="./login" method="post">
                            <div class="p-4">
                            
                                <div class="input-group mb-3">
                                
                                    <span class="input-group-text bg-success">
                                    	<i class="fa fa-user" aria-hidden="true"></i>
                                    </span>
                                    <input type="email" name="email" class="form-control" placeholder="email">
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text bg-success">
                                    	<i class="fa fa-key" aria-hidden="true"></i>
                                    </span>
                                    <input type="password" name="pwd" class="form-control" placeholder="password">
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                    <label class="form-check-label" for="flexCheckDefault">
                                        Remember Me
                                    </label>
                                </div>
                                <button class="btn btn-dark text-center mt-2" type="submit">
                                    Login
                                </button>
                                <p class="text-center mt-5">Don't have an account?
                                    <span class="text-success">Register</span>
                                </p>
                                <p class="text-center text-dark">Forgot your password?</p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
 <%@include file="layouts/footer.jsp" %>