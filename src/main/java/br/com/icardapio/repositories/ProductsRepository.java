package br.com.icardapio.repositories;

import java.util.List;

import br.com.icardapio.entity.Category;
import br.com.icardapio.entity.Product;

public interface ProductsRepository extends BaseRepository<Product, Long> {

	List<Product> findAllByCategory(Category category);

}
