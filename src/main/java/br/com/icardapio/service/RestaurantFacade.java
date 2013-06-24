package br.com.icardapio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.icardapio.dao.JpaCategoriesDao;
import br.com.icardapio.dao.JpaProductsDao;
import br.com.icardapio.entity.Category;
import br.com.icardapio.entity.Product;
import br.com.icardapio.entity.Restaurant;

@Service
@Transactional(propagation=Propagation.MANDATORY)
public class RestaurantFacade {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private JpaCategoriesDao categoriesDao;
	
	@Autowired
	private JpaProductsDao productsDao;
	
	
	public Restaurant getRestaurant() {
		Restaurant restaurant = new Restaurant();
		restaurant.setName("Dona Maria Pizzaria");
		restaurant.setSlogan("A melhor pizza do Ipiranga");
		restaurant.setPhone("11 3115-2345");
		restaurant.setAddress("Av Dr Gentil de Moura, 850");
		restaurant.setCity("São Paulo");
		
		return restaurant;
	}
	
	public List<Category> getAllCategories() {
		List<Category> categories = categoriesDao.listAll();
		
		if (categories.isEmpty()) {
			for(String categoryName: new String[] { "Pizza", "Massas", "Bebidas", "Sobremesas" }) {
				categoriesDao.saveOrUpdate(new Category(categoryName));
			}
			
			categories = categoriesDao.listAll();
		}
		
		return categories;
	}

	@PreAuthorize("hasRole('admin')")
	public Product addProduct(Product product) {
		return productsDao.saveOrUpdate(product);
	}
	
	@PreAuthorize("hasRole('admin')")
	public Product removeProduct(Long categoryId, Long productId) {
		Category category = categoriesDao.find(categoryId);
		Product product = productsDao.find(productId);
		
		return productsDao.remove(category, product);
	}

}
