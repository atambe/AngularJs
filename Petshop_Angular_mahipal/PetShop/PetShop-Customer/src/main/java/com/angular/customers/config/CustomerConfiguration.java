package com.angular.customers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages={"com.angular.customer.controller","com.angular.core.impservice","com.angular.core.iservice"})
@EnableWebMvc
public class CustomerConfiguration extends  WebMvcConfigurerAdapter
{

	/**
	 * This method is used for mapping the jsp pages
	 * @return viewResolver jsp page
	 */
	/*@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}*/
	
	
}
