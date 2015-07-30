package com.sogeti.webshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sogeti.webshop.helper.JsonHelper;
import com.sogeti.webshop.model.Customer;
import com.sogeti.webshop.model.Invoice;
import com.sogeti.webshop.model.Item;
import com.sogeti.webshop.model.PurchaseOrder;
import com.sogeti.webshop.model.ShoppingCartItem;

@Path("/order")
public class OrderWs {

	@Inject OrderService orderService;
	
	@Inject
	CustomerService customerService;
	
	@GET
	@Path("/createInvoice/{invoice}")
	@Produces(MediaType.APPLICATION_JSON)
	 public PurchaseOrder createInvoice(@PathParam("invoice") String invoiceParam ) {
				
		Invoice invoice = JsonHelper.getInvoicefromJsonObj(invoiceParam);
		List<ShoppingCartItem> cartItems= convertCartItems(invoice) ;
		
		Customer customer= customerService.findCustomer(1323L);		
		
		return orderService.createOrder(customer, cartItems);
	 }

	/**
	 * Covert Json Object to Java Object
	 * @param invoice
	 * @return
	 */
	private List<ShoppingCartItem> convertCartItems(Invoice invoice) {
		
		List<ShoppingCartItem> returnList = new ArrayList<ShoppingCartItem>();
		
		for (Item item : invoice.getItems()) {
			
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(item.getProduct(),item.getQty());
			returnList.add(shoppingCartItem);
		}
		
		return returnList;
	}
}
