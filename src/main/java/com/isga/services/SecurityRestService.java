package com.isga.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.isga.sessions.ISecurityBeanLocal;

@Stateless
@Path("security")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class SecurityRestService {
	@EJB
	private ISecurityBeanLocal security;
}
