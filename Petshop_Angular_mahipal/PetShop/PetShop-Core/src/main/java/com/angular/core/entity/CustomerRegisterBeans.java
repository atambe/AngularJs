package com.angular.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name = "LOGIN")
public class CustomerRegisterBeans implements Serializable{
	private static final long serialVersionUID = -2260640639416152488L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "USERID")
	private int userID;
	@NotNull(message="Please enter the user name")
	@NotBlank(message="Not Blank")
	@Column( name = "USERNAME")
	private String userName;
	@NotNull(message="Please enter the password")
	@NotBlank(message="Not Blank")
	@Column( name = "PASSWORD")
	private String password;
	
	private String passconfirm;

	@NotNull(message="Please enter the email")
	@NotBlank
	@Email(message="Please enter the valid email")
	@Column( name = "EMAIL")
	private String email;
	
	@Column( name = "TYPE")
	private String type;
	
	public int getUserId() {
		return userID;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getPassconfirm() {
		return passconfirm;
	}

	public void setPassconfirm(String passconfirm) {
		this.passconfirm = passconfirm;
	}
}
