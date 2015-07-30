package com.angular.core.impservice;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.angular.core.dao.HibernateUtil;
import com.angular.core.entity.ProductBeans;
import com.angular.core.entity.ProductDisplayBeans;
import com.angular.core.iservice.IProductDisplayService;

@Service
public class ProductDisplayService implements IProductDisplayService {
	private ProductDisplayBeans productBeans;

	/**
	 * This method will display all the available products
	 * 
	 * @return listProducts list of product beans
	 */
	public List<ProductBeans> getProducts() {
		productBeans = new ProductDisplayBeans();
		listProducts();
		return productBeans.getProductList();
	}

	/**
	 * This method will search by product name
	 * 
	 * @param categoryName
	 *            name of the product
	 * @return productNameList list of product beans according to product name
	 */
	public List<ProductBeans> getProductName(String categoryName) {
		List<ProductBeans> products = getProducts();
		List<ProductBeans> productNameList = new ArrayList<ProductBeans>();
		for (ProductBeans porBeans : products) {
			if (porBeans.getProductName().equalsIgnoreCase(categoryName)) {
				productNameList.add(porBeans);
			}
		}
		return productNameList;
	}

	/**
	 * This method will search by group id
	 * 
	 * @param group
	 *            id of the product
	 * @return productNameList list of product beans according to group id
	 */
	public List<ProductBeans> sortByGroup(int categoryName) {
		List<ProductBeans> products = getProducts();
		List<ProductBeans> productNameList = new ArrayList<ProductBeans>();
		for (ProductBeans beans : products) {
			if (beans.getGroupId() == categoryName) {
				productNameList.add(beans);
			}
		}
		return productNameList;
	}

	/**
	 * This method will get product details using product id
	 * 
	 * @param proID
	 *            id of the product
	 * @return productList list of product according to product id
	 */
	public List<ProductBeans> getProductListByID(int proID) {
		List<ProductBeans> products = getProducts();
		List<ProductBeans> productList = new ArrayList<ProductBeans>();
		for (ProductBeans proBeans : products) {
			if (proBeans.getProductId() == proID) {
				productList.add(proBeans);
				break;
			}
		}
		return productList;
	}

	/**
	 * This method is used to fetch products from table
	 */
	@SuppressWarnings("unchecked")
	public void listProducts() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<ProductBeans> products = null;
		try {
			tx = session.beginTransaction();
			Criteria query = session.createCriteria(ProductBeans.class);
			products = query.list();
			tx.commit();
			productBeans.setProductList(products);
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
	}
}
