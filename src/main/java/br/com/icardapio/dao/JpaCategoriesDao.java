package br.com.icardapio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.icardapio.entity.Category;

@Repository
@Transactional(propagation=Propagation.MANDATORY)
public class JpaCategoriesDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Category find(Long id) {
		return entityManager.find(Category.class, id);
	}
	
	public List<Category> listAll() {
		return entityManager.createNamedQuery("listAllCategories", Category.class).getResultList();
	}
	
	public Category saveOrUpdate(Category category) {
		entityManager.persist(category);
		return category;
	}
	
	public Category remove(Category category) {
		entityManager.remove(category);
		return category;
	}

}
