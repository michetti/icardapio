package br.com.icardapio.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

	List<T> findAll();

}
