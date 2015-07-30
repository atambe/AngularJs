package com.sogeti.webshop.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sogeti.webshop.model.Customer;

@Path("/customer")
public class CustomerWs {

	@Inject
	CustomerService customerService;
	
	@POST
	@Path("/findCustomer/{loginname}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	 public Object findCustomer( @PathParam("loginname") final String loginname,
			 @PathParam("password") final String password) {
		Customer cust= customerService.findCustomer(loginname,password);
				
		 return cust;
	 }
}
