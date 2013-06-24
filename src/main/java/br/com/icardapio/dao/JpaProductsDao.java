package br.com.icardapio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.icardapio.entity.Category;
import br.com.icardapio.entity.Product;

@Repository
public class JpaProductsDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Product find(Long id) {
		return entityManager.find(Product.class, id);
	}
	
	public List<Product> listAll() {
		return entityManager.createNamedQuery("listAllProducts", Product.class).getResultList();
	}
	
	public Product saveOrUpdate(Product product) {
		entityManager.persist(product);
		return product;
	}
	
	public Product remove(Category category, Product product) {
		category.getProducts().remove(product);
		return product;
	}
	
}
