package com.isga.filters;

import java.io.PrintWriter;

import javax.ejb.EJB;

import com.google.gson.Gson;
import com.isga.model.ErrorAPIResponse;
import com.isga.sessions.ISecurityBeanLocal;
import com.isga.sessions.SecurityBean;
import com.isga.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JWTVerifyRole {

	public static boolean verify(ISecurityBeanLocal securityBean,String authorization,String rolename) {
		if(authorization != null) {
			String token = authorization.substring(JwtUtils.AUTHENTICATION_SCHEME.length()); 
	    try {
	            Jws<Claims> claims = JwtUtils.validateToken(token);
	            String username = claims.getBody().getSubject();
	            if(securityBean.hasRole(username, rolename)) {
	            	return true;
	            }else {
	            	return false;
	            }
	     } catch (Exception e) {
	           return false;
	        }
		}else {
       	 return false;
		}
	}

}
