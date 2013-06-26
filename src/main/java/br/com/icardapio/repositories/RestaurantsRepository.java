package br.com.icardapio.repositories;

import br.com.icardapio.entity.Restaurant;

public interface RestaurantsRepository extends BaseRepository<Restaurant, Long> {
	
	Restaurant getBySubdomain(String subdomain);
	
}