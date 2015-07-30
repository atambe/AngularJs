package com.angular.core.iservice;

import com.angular.core.entity.CustomerRegisterBeans;

public interface ICustomerRegisterService {

	public void processRegisteration(String name,String email,String userpass);
	public void insertLoginDetails(String userName, String email);
	public String updatePassword(CustomerRegisterBeans beans, String password);
	public void deleteUser(int id);
}
