package com.sogeti.webshop.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement()
public class Invoice {

	private List<Item> items;

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
}
