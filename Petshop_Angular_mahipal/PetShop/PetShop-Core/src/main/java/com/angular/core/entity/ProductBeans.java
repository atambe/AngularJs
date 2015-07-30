package com.angular.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
/**
 * This class is used for perform operations on product master table
 * @author mahijain
 * 
 */

@Entity
@Table(name = "product_master")
public class ProductBeans  implements Serializable{

	private static final long serialVersionUID = -122384335099842323L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "PRODUCTID")
	private int productId;
	
	@NotNull(message="Please enter proper group id")
	@Column( name = "GROUPID")
	private int groupId;
	
	@NotNull(message="Please enter proper product name")
	@Column( name = "PRODUCTNAME")
	private String productName;
	
	@NotNull(message="Please enter proper description")
	@Column( name = "DESCRIPTION")
	private String description;
	
	@NotNull(message="Please enter proper image name")
	@Column( name = "IMGNAME")
	private String imgName;
	
	@Min(value = 1,	message = "Please enter proper price")
	@Column( name = "PRICE")
	private double price;
	
	@Min(value = 1,	message = "Please enter proper quantity")
	@Column( name = "QUANTITY")
	private int quantity;
	
	public int getProductId() {
		return productId;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
