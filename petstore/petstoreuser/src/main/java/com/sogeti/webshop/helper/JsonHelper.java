package com.sogeti.webshop.helper;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sogeti.webshop.model.Invoice;

public class JsonHelper {

	/**
	 * Covert Invoice json string in to Object
	 * @param jsonStr
	 * @return
	 */
	public static Invoice getInvoicefromJsonObj(String jsonStr){
		
		if(StringUtils.isBlank(jsonStr)){
			return null;
		}
		
		Gson gson = new Gson();
		Invoice invoiceObj = gson.fromJson(jsonStr, Invoice.class); 
		
		
		return invoiceObj;
		
	}
}
