package br.com.icardapio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.icardapio.entity.Category;
import br.com.icardapio.entity.Product;
import br.com.icardapio.entity.Restaurant;
import br.com.icardapio.multitenancy.CurrentTenantResolver;
import br.com.icardapio.repositories.CategoriesRepository;
import br.com.icardapio.repositories.ProductsRepository;
import br.com.icardapio.repositories.RestaurantsRepository;

@Service
@Transactional(propagation=Propagation.MANDATORY)
public class RestaurantFacade {
	
	@Autowired
	private CurrentTenantResolver<Long> tenantResolver;
	
	@Autowired
	private RestaurantsRepository restaurantsRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private ProductsRepository productsRepository;
	
	public Restaurant getRestaurant() {
		return restaurantsRepository.findOne(tenantResolver.getCurrentTenantId());
	}
	
	public List<Category> getAllCategories() {
		List<Category> categories = categoriesRepository.findAll();
		
		for(Category category: categories) {
			category.setProducts(productsRepository.findAllByCategory(category));
		}
		
		return categories;
	}

	@PreAuthorize("hasRole('admin')")
	public Product addProduct(Product product) {
		return productsRepository.save(product);
	}
	
	@PreAuthorize("hasRole('admin')")
	public Product removeProduct(Long categoryId, Long productId) {
		Category category = categoriesRepository.findOne(categoryId);
		Product product = productsRepository.findOne(productId);
		
		category.getProducts().remove(product);
		return product;
	}
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantsRepository.save(restaurant);
	}
	
	public void createMasterData() {
		
		// create the master tenant
		if (restaurantsRepository.getBySubdomain("www") == null) {
			Restaurant restaurant = new Restaurant();
			restaurant.setName("iCardapio");
			restaurant.setSubdomain("www");
			restaurant.setSlogan("Seu Cardapio na Internet");
			restaurant.setPhone("11 3114-2334");
			restaurant.setAddress("Av Dr Gentil de Moura, 850");
			restaurant.setCity("Sao Paulo");
			
			restaurantsRepository.save(restaurant);
		}
		
		// create some categories
		if (categoriesRepository.count() <= 0) {
			for(String categoryName: new String[] { "Pizza", "Massas", "Bebidas", "Sobremesas" }) {
				categoriesRepository.save(new Category(categoryName));
			}
		}
		
	}

}
